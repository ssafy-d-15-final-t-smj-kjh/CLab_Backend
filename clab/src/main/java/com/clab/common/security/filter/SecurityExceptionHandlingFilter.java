package com.clab.common.security.filter;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
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
				setErrorResponse(response, HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED");
			}
			else if (e instanceof JwtException) {
				log.debug("JWT 예외 발생: {}, {}", request.getRequestURI(), e);
				setErrorResponse(response, HttpStatus.UNAUTHORIZED, "TOKEN_INVALID");
			} else if (e instanceof BadCredentialsException) {
				setErrorResponse(response, HttpStatus.UNAUTHORIZED, e.getMessage());
			} else {
				setErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
	}

	private void setErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
		log.debug("커스텀 필터에서 발생한 예외 처리: {}, {}", status, message);
		response.setStatus(status.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> errorDetails = Map.of("status", status.value(), "message", message);

		response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
	}
}
