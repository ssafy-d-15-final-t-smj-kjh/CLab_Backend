package com.clab.common.security.filter;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clab.common.exception.ApiResponse;
import com.clab.common.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityExceptionHandlingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			if (e instanceof ExpiredJwtException) {
				log.debug("JWT 만료 예외 발생: {}, {}", request.getRequestURI(), e);
				setErrorResponse(response, ErrorCode.TOKEN_EXPIRED);
			} else if (e instanceof JwtException) {
				log.debug("JWT 예외 발생: {}, {}", request.getRequestURI(), e);
				setErrorResponse(response, ErrorCode.TOKEN_INVALID);
			} else if (e instanceof BadCredentialsException) {
				setErrorResponse(response, ErrorCode.UNAUTHORIZED);
			} else {
				setErrorResponse(response, ErrorCode.INTERNAL_SERVER_ERROR);
			}
		}
	}

	private void setErrorResponse(HttpServletResponse response,ErrorCode errorCode) throws IOException {
		log.debug("커스텀 필터에서 발생한 예외 처리: {}, {}", errorCode.getStatus(), errorCode.getMessage());

        ApiResponse<?> apiResponse = new ApiResponse<>(errorCode, null);
        response.setStatus(errorCode.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
	}
}
