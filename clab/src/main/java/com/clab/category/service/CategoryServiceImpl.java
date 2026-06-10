package com.clab.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clab.category.dao.CategoryMapper;
import com.clab.category.dto.CategoryDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryMapper categoryMapper;

	@Override
	public List<CategoryDto> findAll() {
		return categoryMapper.findAll();
	}

	@Override
	public CategoryDto findById(int id) {
		return categoryMapper.findById(id);
	}

	@Override
	public void insert(CategoryDto dto) {
		categoryMapper.insert(dto);
	}

	@Override
	public void update(int id, CategoryDto dto) {
		categoryMapper.update(id, dto);
	}

	@Override
	public void delete(int id) {
		categoryMapper.delete(id);
	}

}
