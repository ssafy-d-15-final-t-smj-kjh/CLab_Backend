package com.clab.chat.service;

import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import com.clab.chat.dao.ChatMapper;
import com.clab.chat.dto.ChatDto;
import com.clab.common.exception.ApiResponse;
import com.clab.common.exception.CustomException;
import com.clab.common.exception.ErrorCode;
import com.clab.common.exception.SuccessCode;

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
		ChatDto result = chatMapper.findById(id);
		if(result == null) {
			throw new CustomException(ErrorCode.CHAT_NOT_FOUND);
		} else {
			return result;
		}
	}

	@Override
	public int insert(ChatDto dto) {
		int result = chatMapper.insert(dto);
		if(result == 0) {
			throw new CustomException(ErrorCode.CHAT_BAD_REQUEST);
		} else {
			return result;
		}
	}

	@Override
	public int update(int id, ChatDto dto) {
		int result = chatMapper.update(id, dto);
		if(result == 0) {
			throw new CustomException(ErrorCode.CHAT_BAD_REQUEST);
		} else {
			return result;
		}
	}

	@Override
	public int delete(int id) {
		int result = chatMapper.delete(id);
		if(result == 0) {
			throw new CustomException(ErrorCode.CHAT_BAD_REQUEST);
		} else {
			return result;
		}
	}
	
}
