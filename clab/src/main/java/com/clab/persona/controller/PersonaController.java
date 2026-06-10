package com.clab.persona.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clab.persona.dto.PersonaDto;
import com.clab.persona.service.PersonaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persona")
@Tag(name = "PersonaController", description = "인물상 관리 API")
public class PersonaController {
	
	private final PersonaService personaService;
	
	@GetMapping
	public List<PersonaDto> findAll(){
		return personaService.findAll();
	}
	@GetMapping("/{id}")
	public PersonaDto findById(@PathVariable("id") int id) {
		return personaService.findById(id);
	}
	@PostMapping
	public void insert(@RequestBody PersonaDto dto) {
		personaService.insert(dto);
	}
	@PatchMapping("/{id}")
	public void update(@PathVariable("id") int id, @RequestBody PersonaDto dto) {
		personaService.update(id, dto);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		personaService.delete(id);
	}
}
