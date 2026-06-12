package com.clab.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clab.member.dao.MemberMapper;
import com.clab.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	private final MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MemberDto member = memberMapper.findByEmail(email);
		if (member == null) {
			throw new UsernameNotFoundException("존재하지 않는 이메일입니다.");
		}
		return new CustomUserDetails(member);
	}

}
