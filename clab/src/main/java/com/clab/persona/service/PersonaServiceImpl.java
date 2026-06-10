package com.clab.persona.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
		return personaMapper.findById(id);
	}

	@Override
	public void insert(PersonaDto dto) {
		personaMapper.insert(dto);
	}

	@Override
	public void update(int id, PersonaDto dto) {
		personaMapper.update(id, dto);
	}

	@Override
	public void delete(int id) {
		personaMapper.delete(id);
	}

}
