package com.clab.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clab.auth.dto.LoginDto;
import com.clab.auth.service.AuthService;
import com.clab.common.exception.ApiResponse;
import com.clab.common.exception.SuccessCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "인증 관리 API")
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/login")
	@Operation(summary = "로그인")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto) {
		Map<String, String> tokens = authService.login(loginDto);
		ApiResponse response = new ApiResponse(SuccessCode.SELECT_SUCCESS, tokens);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@PostMapping("/refresh")
	@Operation(summary="access token 토큰 재발급")
	public ResponseEntity<ApiResponse> refresh(@RequestHeader("Refresh-Token") String refreshToken) {
		String accessToken = authService.refresh(refreshToken);
		ApiResponse response = new ApiResponse(SuccessCode.UPDATE_SUCCESS, accessToken);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping("/logout")
	@Operation(summary="로그아웃")
	public ResponseEntity<ApiResponse> logout(@RequestHeader("Refresh-Token") String refreshToken) {
		authService.logout(refreshToken);
		ApiResponse response = new ApiResponse(SuccessCode.UPDATE_SUCCESS, "로그아웃에 성공하였습니다.");
		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
