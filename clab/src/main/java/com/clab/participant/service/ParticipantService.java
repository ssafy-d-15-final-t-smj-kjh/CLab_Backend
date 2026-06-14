package com.clab.participant.service;

import java.util.List;

import com.clab.participant.dto.ParticipantDto;

public interface ParticipantService {

	List<ParticipantDto> findAll();

	ParticipantDto findById(int id);

	List<ParticipantDto> findByChatId(int chatId);

	int insert(ParticipantDto dto);

	void delete(int id);

	void deleteByChatId(int chatId);

}
