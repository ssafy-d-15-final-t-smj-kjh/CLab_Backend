package com.clab.common.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.clab.common.security.CustomUserDetails;
import com.clab.common.security.jwt.JWTUtil;
import com.clab.member.dto.MemberDto;
import com.clab.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final MemberService memberService;
	private final JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
			MemberService memberService, JWTUtil jwtUtil) {
		super(authenticationManager);
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
		this.setFilterProcessesUrl("/member/login");
		this.setUsernameParameter("email");
		this.setPasswordParameter("password");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Map<String, String> credentials = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			String email = credentials.get("email");
			String password = credentials.get("password");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
			return getAuthenticationManager().authenticate(token);
		} catch (IOException e) {
			throw new AuthenticationServiceException("요청 본문을 읽을 수 없습니다.", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<>();
		
		CustomUserDetails details = (CustomUserDetails)authentication.getPrincipal();
		MemberDto member = details.getMember();
		String accessToken = jwtUtil.createAccessToken(member);
		String refreshToken = jwtUtil.createRefreshToken(member);
		result.put("accessToken", accessToken);
		result.put("refreshToken", refreshToken);
		memberService.updateRefreshToken(member.getUserId(), refreshToken);
		handleResult(response, result, HttpStatus.OK);
		
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		throw failed;
	}
	
    // 결과 전송 helper 메소드
    private void handleResult(HttpServletResponse response, Map<String, ?> data,
            HttpStatus status) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            String jsonResponse = new ObjectMapper().writeValueAsString(data);
            response.setStatus(status.value());
            response.getWriter().write(jsonResponse);
        } catch (IOException e) {
            log.error("JSON 처리 실패", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
	
	
}
