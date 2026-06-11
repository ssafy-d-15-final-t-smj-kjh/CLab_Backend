package com.clab.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.clab.chat.dto.ChatDto;

@Mapper
public interface ChatMapper {

	List<ChatDto> findAll();

	ChatDto findById(int id);

	void insert(ChatDto dto);

	void update(@Param("id") int id, @Param("dto") ChatDto dto);

	void delete(int id);

}
