package com.clab.persona.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clab.common.exception.CustomException;
import com.clab.common.exception.ErrorCode;
import com.clab.persona.dao.PersonaMapper;
import com.clab.persona.dto.PersonaDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {
	
	private final PersonaMapper personaMapper;

	@Override
	public List<PersonaDto> findAll() {
		return personaMapper.findAll();
	}

	@Override
	public PersonaDto findById(int id) {
		PersonaDto result = personaMapper.findById(id);
		if(result == null) {
			throw new CustomException(ErrorCode.PERSONA_NOT_FOUND);
		} else {
			return result;
		}
	}

	@Override
	public int insert(PersonaDto dto) {
		int result = personaMapper.insert(dto);
		if(result == 0) {
			throw new CustomException(ErrorCode.PERSONA_INSERT_FAILED);
		} else {
			return dto.getId();
		}
	}

	@Override
	public void update(int id, PersonaDto dto) {
		int result = personaMapper.update(id, dto);
		if(result == 0) {
			throw new CustomException(ErrorCode.PERSONA_UPDATE_FAILED);
		}
	}

	@Override
	public void delete(int id) {
		int result = personaMapper.delete(id);
		if(result == 0) {
			throw new CustomException(ErrorCode.PERSONA_DELETE_FAILED);
		}
	}

}
