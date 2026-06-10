package com.clab.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryDto {
	private Integer id;
	private String name;
	private String description;
	private double weight;
}
