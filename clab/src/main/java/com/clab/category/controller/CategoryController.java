package com.clab.category.controller;

import java.util.List;

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

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Tag(name = "CategoryController", description = "카테고리 관리 API")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping
	public List<CategoryDto> findAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("/{id}")
	public CategoryDto findById(int id) {
		return categoryService.findById(id);
	}
	
	@PostMapping
	public void insert(@RequestBody CategoryDto dto) {
		categoryService.insert(dto);
	}
	
	@PatchMapping("/{id}")
	public void update(@PathVariable("id") int id, @RequestBody CategoryDto dto) {
		categoryService.update(id, dto);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		categoryService.delete(id);
	}
}
