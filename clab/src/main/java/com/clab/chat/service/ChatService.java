package com.clab.chat.service;

import java.util.List;

import com.clab.chat.dto.ChatDto;

public interface ChatService {
	List<ChatDto> findAll();

	ChatDto findById(int id);

	void insert(ChatDto dto);

	void update(int id, ChatDto dto);

	void delete(int id);
}
