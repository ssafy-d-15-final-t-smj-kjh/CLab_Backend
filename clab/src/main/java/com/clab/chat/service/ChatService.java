package com.clab.chat.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.clab.chat.dto.ChatDto;
import com.clab.common.exception.ApiResponse;

public interface ChatService {
	List<ChatDto> findAll();

	ChatDto findById(int id);

	int insert(ChatDto dto, MultipartFile file);

	void update(int id, ChatDto dto);

	void delete(int id);
}
