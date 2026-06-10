package com.clab.chat.controller;

import java.util.List;

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

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Tag(name = "ChatController", description = "대화 관리 API")
public class ChatController {
	
	private final ChatService chatService;
	
	@GetMapping
	public List<ChatDto> findAll(){
		return chatService.findAll();
	}
	
	@GetMapping("/{id}")
	public ChatDto findById(@PathVariable("id") int id) {
		return chatService.findById(id);
	}
	
	@PostMapping
	public void insert(@RequestBody ChatDto dto) {
		chatService.insert(dto);
	}
	
	@PatchMapping("/{id}")
	public void update(@PathVariable("id") int id, @RequestBody ChatDto dto) {
		chatService.update(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		chatService.delete(id);
	}

}
