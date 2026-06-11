package com.clab.common.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.clab.member.dto.MemberDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTUtil {
	private final long accessExpMin;
	private final long refreshExpMin;
	private final SecretKey key;

	public JWTUtil(@Value("${clab.jwt.access-expmin}") long accessExpmin,
			@Value("${clab.jwt.refresh-expmin}") long refreshExpmin,
			@Value("${clab.jwt.secret-string}") String secretKeyString) {
		this.accessExpMin = accessExpmin;
		this.refreshExpMin = refreshExpmin;
		this.key = Keys.hmacShaKeyFor(secretKeyString.getBytes());

		log.debug("jwt secret key: {}", Arrays.toString(key.getEncoded()));
	}

	public String createAccessToken(MemberDto member) {
		return create("accessToken", accessExpMin, Map.of("email", member.getEmail()));
	}

	public String createRefreshToken(MemberDto member) {
		return create("refreshToken", refreshExpMin, Map.of("email", member.getEmail()));
	}

	public String create(String subject, long expireMin, Map<String, Object> claims) {
		Date expireDate = new Date(System.currentTimeMillis() + 1000 * 60 * expireMin);

		String jwt = Jwts.builder().subject(subject).claims(claims).expiration(expireDate).signWith(key).compact();

		return jwt;
	}

	public Claims getClaims(String jwt) {
		JwtParser parser = Jwts.parser().verifyWith(key).build();

		Jws<Claims> jws = parser.parseSignedClaims(jwt);
		return jws.getPayload();
	}
}
