package com.clab.chatFile.dao;

import org.apache.ibatis.annotations.Mapper;

import com.clab.chatFile.dto.ChatFileDto;

@Mapper
public interface ChatFileMapper {
	ChatFileDto findById(int id);

	int insert(ChatFileDto dto);

	int delete(int id);
}
