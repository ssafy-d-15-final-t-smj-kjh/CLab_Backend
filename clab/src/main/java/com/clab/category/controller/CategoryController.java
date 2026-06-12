package com.clab.category.controller;

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

import com.clab.category.dto.CategoryDto;
import com.clab.category.service.CategoryService;
import com.clab.common.exception.ApiResponse;
import com.clab.common.exception.SuccessCode;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Tag(name = "CategoryController", description = "카테고리 관리 API")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<ApiResponse> findAll() {
		List<CategoryDto> result = categoryService.findAll();
		ApiResponse response = new ApiResponse(SuccessCode.SELECT_SUCCESS, result);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> findById(int id) {
		CategoryDto result = categoryService.findById(id);
		ApiResponse response = new ApiResponse(SuccessCode.SELECT_SUCCESS, result);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> insert(@RequestBody CategoryDto dto) {
		int id = categoryService.insert(dto);
		ApiResponse response = new ApiResponse(SuccessCode.INSERT_SUCCESS, Map.of("id", id));
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponse> update(@PathVariable("id") int id, @RequestBody CategoryDto dto) {
		categoryService.update(id, dto);
		ApiResponse response = new ApiResponse(SuccessCode.UPDATE_SUCCESS, null);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable("id") int id) {
		categoryService.delete(id);
		ApiResponse response = new ApiResponse(SuccessCode.DELETE_SUCCESS, null);
		return ResponseEntity
				.status(response.getStatus())
				.body(response);
	}
}
