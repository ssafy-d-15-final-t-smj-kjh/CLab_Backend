package com.clab.persona.service;

import java.util.List;

import com.clab.persona.dto.PersonaDto;

public interface PersonaService {

	List<PersonaDto> findAll();

	PersonaDto findById(int id);

	void insert(PersonaDto dto);

	void update(int id, PersonaDto dto);

	void delete(int id);

}
