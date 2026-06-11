package com.clab.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clab.chat.dao.ChatMapper;
import com.clab.chat.dto.ChatDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
	
	private final ChatMapper chatMapper;

	@Override
	public List<ChatDto> findAll() {
		return chatMapper.findAll();
	}

	@Override
	public ChatDto findById(int id) {
		return chatMapper.findById(id);
	}

	@Override
	public void insert(ChatDto dto) {
		chatMapper.insert(dto);
	}

	@Override
	public void update(int id, ChatDto dto) {
		chatMapper.update(id, dto);
	}

	@Override
	public void delete(int id) {
		chatMapper.delete(id);
	}
	
}
