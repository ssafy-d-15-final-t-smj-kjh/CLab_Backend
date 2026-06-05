package com.clab.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clab.member.dao.MemberMapper;
import com.clab.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper mapper;

	@Override
	public List<MemberDto> findAll() {
		return mapper.findAll();
	}

	@Override
	public MemberDto findById(int id) {
		return mapper.findById(id);
	}

	@Override
	public void insert(MemberDto dto) {
		mapper.insert(dto);
	}

	@Override
	public void update(int id, MemberDto dto) {
		dto.setUserId(id);
		mapper.update(dto);
	}

	@Override
	public void delete(int id) {
		mapper.delete(id);
	}

}
