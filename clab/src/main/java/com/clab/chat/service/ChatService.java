package com.clab.chat.service;

import java.util.List;

import com.clab.chat.dto.ChatDto;
import com.clab.common.exception.ApiResponse;

public interface ChatService {
	List<ChatDto> findAll();

	ChatDto findById(int id);

	int insert(ChatDto dto);

	int update(int id, ChatDto dto);

	int delete(int id);
}
