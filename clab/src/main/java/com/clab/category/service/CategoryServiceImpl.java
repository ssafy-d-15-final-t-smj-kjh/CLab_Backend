package com.clab.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clab.category.dao.CategoryMapper;
import com.clab.category.dto.CategoryDto;
import com.clab.common.exception.CustomException;
import com.clab.common.exception.ErrorCode;

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
		CategoryDto result = categoryMapper.findById(id);
		if(result == null) {
			throw new CustomException(ErrorCode.CATEGORY_BAD_REQUEST);
		} else {
			return result;
		}
	}

	@Override
	public int insert(CategoryDto dto) {
		int result = categoryMapper.insert(dto);
		if(result == 0) {
			throw new CustomException(ErrorCode.CATEGORY_INSERT_FAILED);
		} else {
			return dto.getId();
		}
	}

	@Override
	public void update(int id, CategoryDto dto) {
		int result = categoryMapper.update(id, dto);
		if(result == 0) {
			throw new CustomException(ErrorCode.CATEGORY_UPDATE_FAILED);
		}
	}

	@Override
	public void delete(int id) {
		int result = categoryMapper.delete(id);
		if(result == 0) {
			throw new CustomException(ErrorCode.CATEGORY_DELETE_FAILED);
		}
	}

}
