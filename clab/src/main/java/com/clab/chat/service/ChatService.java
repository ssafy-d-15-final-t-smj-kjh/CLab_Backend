package com.clab.chat.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.clab.chat.dto.ChatDto;
import com.clab.common.exception.ApiResponse;

public interface ChatService {
	
	List<ChatDto> findAll();
	
	List<ChatDto> findAllByUserId(int userId);
	
	ChatDto findById(int userId, int id);

	int insert(ChatDto dto, MultipartFile file, int userId);

	int update(int userId, int id, ChatDto dto);

	int delete(int userId, int id);
}
