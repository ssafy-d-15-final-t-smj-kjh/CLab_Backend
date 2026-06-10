package com.clab.category.service;

import java.util.List;

import com.clab.category.dto.CategoryDto;

public interface CategoryService {

	List<CategoryDto> findAll();

	CategoryDto findById(int id);

	void insert(CategoryDto dto);

	void update(int id, CategoryDto dto);

	void delete(int id);

}
