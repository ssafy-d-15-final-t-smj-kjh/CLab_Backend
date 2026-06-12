package com.clab.persona.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.clab.persona.dto.PersonaDto;

@Mapper
public interface PersonaMapper {

	List<PersonaDto> findAll();

	PersonaDto findById(int id);

	int insert(PersonaDto dto);

	int update(int id, PersonaDto dto);

	int delete(int id);

}
