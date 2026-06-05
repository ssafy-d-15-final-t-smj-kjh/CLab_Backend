package com.clab.member.service;

import java.util.List;

import com.clab.member.dto.MemberDto;

public interface MemberService {
	List<MemberDto> findAll();
	MemberDto findById(int id);
	void insert(MemberDto dto);
	void update(int id, MemberDto dto);
	void delete(int id);
}
