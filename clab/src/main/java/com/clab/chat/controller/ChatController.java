package com.clab.chat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clab.chat.dto.ChatDto;
import com.clab.chat.service.ChatService;
import com.clab.common.exception.ApiResponse;
import com.clab.common.exception.SuccessCode;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Tag(name = "ChatController", description = "대화 관리 API")
public class ChatController {
	
	private final ChatService chatService;
	
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
	
	@PostMapping
	public ResponseEntity<ApiResponse> insert(@RequestBody ChatDto dto) {
		int rows = chatService.insert(dto);
		ApiResponse response = new ApiResponse(SuccessCode.INSERT_SUCCESS, String.format("추가된 행 수 : %d", rows));
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable("id") int id, @RequestBody ChatDto dto) {
		int rows = chatService.update(id, dto);
		ApiResponse response = new ApiResponse(SuccessCode.UPDATE_SUCCESS, String.format("변경된 행 수 : %d", rows));
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable("id") int id) {
		int rows = chatService.delete(id);
		ApiResponse response = new ApiResponse(SuccessCode.DELETE_SUCCESS, String.format("삭제된 행 수 : %d", rows));
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}

}
