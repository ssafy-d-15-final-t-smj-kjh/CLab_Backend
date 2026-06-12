package com.clab.common.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.clab.common.security.CustomUserDetailService;
import com.clab.common.security.filter.JWTAuthenticationFilter;
import com.clab.common.security.filter.JWTVerificationFilter;
import com.clab.common.security.filter.SecurityExceptionHandlingFilter;

@Configuration
public class APISecurityConfig {
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Order(1) // 낮을 수록 우선순위가 높음. 생략 시 가장 낮음
	SecurityFilterChain apiSecurityFilterChain(HttpSecurity http,
			@Qualifier("corsConfigurationSource") CorsConfigurationSource corsConfig,
			CustomUserDetailService userDetailsService, JWTAuthenticationFilter authFilter,
			JWTVerificationFilter jwtVerifyFilter, SecurityExceptionHandlingFilter exceptionFilter) throws Exception {
		// TODO: 06-2. SecurityFilterChain을 생성한다.
		// 이 filter chain은 /api/**를 대상으로 하며 cors, userdetails, csrf, session 관련 설정을 해보자.
		// /api/v1/auth/**, /api/v1/etc/** 경로는 permitall 하고 /api/v1/members 는 인증을 요청한다.
		// 적절한 순서로 Filter의 위치를 지정한다.
		http.securityMatcher("/**").userDetailsService(userDetailsService)
				.cors(cors -> cors.configurationSource(corsConfig))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable());
		// END
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/member/join", "/member/refresh", "/member/logout").permitAll()
				.requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
				.anyRequest().authenticated());
		http.addFilterAt(authFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(jwtVerifyFilter, JWTAuthenticationFilter.class)
				.addFilterBefore(exceptionFilter, JWTVerificationFilter.class);
		return http.build();
	}

	// TODO: 06-3. Filter 수준에서 동작하기 위한 CorsConfigurationSource를 구성하고 적용하자.
	// WebMvcConfigurer는 cors 설정은 무의미. 왜냐하면 Filter단계에서 이미 차단되기 때문.
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
		configuration.setAllowedHeaders(Arrays.asList("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
