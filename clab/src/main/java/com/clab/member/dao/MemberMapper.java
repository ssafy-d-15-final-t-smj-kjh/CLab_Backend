package com.clab.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.clab.member.dto.MemberDto;

@Mapper
public interface MemberMapper {

	List<MemberDto> findAll();

	MemberDto findById(int id);
	
	MemberDto findByEmail(String email);

	void insert(MemberDto dto);

	void update(int id, MemberDto dto);

	void updateRefreshToken(int id, String refreshToken);
	
	void delete(int id);

}
