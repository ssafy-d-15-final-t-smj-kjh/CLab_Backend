package com.clab.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.clab.member.dto.MemberDto;

@Mapper
public interface MemberMapper {

	List<MemberDto> findAll();

	MemberDto findById(int id);

	void insert(MemberDto dto);

	void update(MemberDto dto);

	void delete(int id);

}
