package com.clab.category.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.clab.category.dto.CategoryDto;

@Mapper
public interface CategoryMapper {

	List<CategoryDto> findAll();

	CategoryDto findById(int id);

	void insert(CategoryDto dto);

	void update(int id, CategoryDto dto);

	void delete(int id);

}
