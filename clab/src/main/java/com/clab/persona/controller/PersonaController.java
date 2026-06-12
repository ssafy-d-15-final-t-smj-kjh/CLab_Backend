package com.clab.persona.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clab.common.exception.ApiResponse;
import com.clab.common.exception.SuccessCode;
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
	public ResponseEntity<ApiResponse> findAll(){
		List<PersonaDto> result = personaService.findAll();
		ApiResponse response = new ApiResponse(SuccessCode.SELECT_SUCCESS, result);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> findById(@PathVariable("id") int id) {
		PersonaDto result = personaService.findById(id);
		ApiResponse response = new ApiResponse(SuccessCode.SELECT_SUCCESS, result);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> insert(@RequestBody PersonaDto dto) {
		int id = personaService.insert(dto);
		ApiResponse response = new ApiResponse(SuccessCode.INSERT_SUCCESS, Map.of("id", id));
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable("id") int id, @RequestBody PersonaDto dto) {
		personaService.update(id, dto);
		ApiResponse response = new ApiResponse(SuccessCode.UPDATE_SUCCESS, null);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable("id") int id) {
		personaService.delete(id);
		ApiResponse response = new ApiResponse(SuccessCode.DELETE_SUCCESS, null);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
}
