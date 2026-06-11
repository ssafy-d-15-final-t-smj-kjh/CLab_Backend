package com.clab.member.service;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clab.common.security.jwt.JWTUtil;
import com.clab.member.dao.MemberMapper;
import com.clab.member.dto.MemberDto;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	private final JWTUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;

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
		if (mapper.findByEmail(dto.getEmail()) != null) {
			return;
		}
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		MemberDto member = new MemberDto(null, dto.getId(), encodedPassword, dto.getUsername(), dto.getEmail(), dto.getImage(), null);
		mapper.insert(member);
	}

	@Override
	public void update(int id, MemberDto dto) {
		mapper.update(id, dto);
	}

	@Override
	public void updateRefreshToken(int id, String refreshToken) {
		mapper.updateRefreshToken(id, refreshToken);
	}

	@Override
	public String refresh(String refreshToken) {
		Claims claims = jwtUtil.getClaims(refreshToken); 
		String email = claims.get("email", String.class);

		MemberDto member = mapper.findByEmail(email);
		if (member == null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
		}

		if (!refreshToken.equals(member.getRefreshToken())) {
			throw new BadCredentialsException("유효하지 않은 Refresh Token입니다.");
		}

		return jwtUtil.createAccessToken(member);
	}
	
	@Override
	public void logout(String refreshToken) {
		Claims claims = jwtUtil.getClaims(refreshToken); 
		String email = claims.get("email", String.class);
		
		MemberDto member = mapper.findByEmail(email);
		if (member == null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
		}

		if (!refreshToken.equals(member.getRefreshToken())) {
			throw new BadCredentialsException("유효하지 않은 Refresh Token입니다.");
		}
		
		mapper.updateRefreshToken(member.getUserId(), null);
	}
	
	@Override
	public void delete(int id) {
		mapper.delete(id);
	}



}
