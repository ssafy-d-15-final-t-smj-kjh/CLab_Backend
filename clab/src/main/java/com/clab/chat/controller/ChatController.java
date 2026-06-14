package com.clab.chat.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clab.chat.dto.ChatDto;
import com.clab.chat.service.ChatService;
import com.clab.chat_file.dto.ParsedMessage;
import com.clab.chat_file.service.ChatParserService;
import com.clab.common.exception.ApiResponse;
import com.clab.common.exception.SuccessCode;
import com.clab.content.dto.ContentDto;
import com.clab.content.service.ContentService;
import com.clab.participant.dto.ParticipantDto;
import com.clab.participant.service.ParticipantService;

import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Tag(name = "ChatController", description = "대화 관리 API")
public class ChatController {
	
	private final ChatService chatService;
	private final ChatParserService charParserService;
	private final ParticipantService participantService;
	private final ContentService contentService;
	
	@GetMapping
	public ResponseEntity<ApiResponse> findAll(){
		List<ChatDto> result = chatService.findAll();
		ApiResponse response = new ApiResponse(SuccessCode.SELECT_SUCCESS, result);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> findById(@PathVariable("id") int id) {
		ChatDto result = chatService.findById(id);
		ApiResponse response = new ApiResponse(SuccessCode.SELECT_SUCCESS, result);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse> insert(
			@RequestBody ChatDto dto,
			@RequestPart("file") MultipartFile file
			) throws IOException {
		int chatId = chatService.insert(dto, file);
		List<ParsedMessage> messages = charParserService.parseChatLog(file);
		List<String> participantNames = charParserService.extractParticipants(messages);
		
		for (String name : participantNames) {
            // 해당 참여자 메시지 필터링
            List<ParsedMessage> myMessages = messages.stream()
                .filter(m -> m.getSender().equals(name))
                .collect(Collectors.toList());
            
            ParticipantDto participantDto = new ParticipantDto();
            participantDto.setChatId(chatId);
            participantDto.setName(name);
            participantDto.setCount(myMessages.size());
            participantDto.setChatLength(
                (long) myMessages.stream()
                    .mapToInt(m -> m.getContent().length())
                    .sum()
            );
            participantService.insert(participantDto);
            
            int participantId = participantDto.getId();
            
            for(ParsedMessage m : myMessages) {
            	ContentDto c = new ContentDto(null, participantId, m.getContent(), m.getTime());
            	contentService.insert(c);
            }
		}
		
		ApiResponse response = new ApiResponse(SuccessCode.INSERT_SUCCESS, Map.of("id", chatId));
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable("id") int id, @RequestBody ChatDto dto) {
		chatService.update(id, dto);
		ApiResponse response = new ApiResponse(SuccessCode.UPDATE_SUCCESS, null);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable("id") int id) {
		chatService.delete(id);
		ApiResponse response = new ApiResponse(SuccessCode.DELETE_SUCCESS, null);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}

}
