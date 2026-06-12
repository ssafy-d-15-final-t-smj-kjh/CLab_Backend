package com.clab.common.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clab.common.security.CustomUserDetailService;
import com.clab.common.security.jwt.JWTUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTVerificationFilter extends OncePerRequestFilter{

	private final JWTUtil jwtUtil;
	private final CustomUserDetailService userDetailService;
	
	private String extractToken(HttpServletRequest request) {		
		String token = request.getHeader("Authorization");
		if(token!=null && token.startsWith("Bearer ")) {
			return token.substring(7);
		}
		return null;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = extractToken(request);
		if(token==null) {
			filterChain.doFilter(request, response);
			return;
		}
		Claims claims = jwtUtil.getClaims(token);
		UserDetails userDetails = userDetailService.loadUserByUsername(claims.get("email").toString());
		var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
	

}
