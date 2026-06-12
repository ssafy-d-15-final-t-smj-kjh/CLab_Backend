package com.clab.auth.service;

import java.util.Map;

import com.clab.auth.dto.LoginDto;

public interface AuthService {
	Map<String, String> login(LoginDto dto);
	String refresh(String refreshToken);
	void updateRefreshToken(int id, String refreshToken);
	void logout(String refreshToken);
}
