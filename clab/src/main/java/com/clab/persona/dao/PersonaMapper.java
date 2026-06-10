package com.clab.persona.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.clab.persona.dto.PersonaDto;

@Mapper
public interface PersonaMapper {

	List<PersonaDto> findAll();

	PersonaDto findById(int id);

	void insert(PersonaDto dto);

	void update(int id, PersonaDto dto);

	void delete(int id);

}
