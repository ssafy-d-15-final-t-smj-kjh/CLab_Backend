package com.clab.participant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.clab.participant.dto.ParticipantDto;

@Mapper
public interface ParticipantMapper {

	List<ParticipantDto> findAll();

	ParticipantDto findById(int id);

	List<ParticipantDto> findByChatId(int chatId);

	int insert(ParticipantDto dto);

	int update(int id, ParticipantDto dto);

	int delete(int id);

	int deleteByChatId(int chatId);

}
