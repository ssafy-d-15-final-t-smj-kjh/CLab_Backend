package com.clab.common.security.jwt;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clab.member.dto.MemberDto;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class JWTUtilTest {
	@Autowired
	JWTUtil util;
	
	
    @Test
    void 토큰_생성테스트() {
        // given
        MemberDto member = new MemberDto(1,"admin@ssafy.com","1234","admin",null);

        // when
        int expireMin = 1;
        String token = util.create("accessToken", expireMin, Map.of("email", member.getEmail()));
        log.debug("토큰 : {}", token);
        // then
        Assertions.assertNotNull(token);
    }

    @Test
    void claim_확인테스트() {
        // given
    	MemberDto member = new MemberDto(1,"admin@ssafy.com","1234","admin",null);

        int expireMin = 1;
        String token = util.create("accessToken", expireMin, Map.of("email", member.getEmail()));
        
        // when
        Map<String, Object> claims = util.getClaims(token);
        // then
        Assertions.assertEquals(member.getEmail(), claims.get("email"));
    }

    @Test
    void 유효기간이_지난_토큰_예외_확인() {
        // given
    	MemberDto member = new MemberDto(1,"admin@ssafy.com","1234","admin",null);
        
        int expireMin = -1; // 지난 시간으로 토큰 생성
        String token = util.create("accessToken", expireMin, Map.of("email", member.getEmail()));
        
        // when & then
        Assertions.assertThrows(ExpiredJwtException.class, () -> util.getClaims(token));
    }

    @Test
    void 형식이_잘못된_토큰_예외_확인() {
        // given
        String token = "wrongtoken";
        // when & then
        Assertions.assertThrows(MalformedJwtException.class, () -> util.getClaims(token));
    }

    @Test
    void 정보가_오염된_토큰_예외_확인() {
        // given
        // 이전의 어떤 키로 만들어진 토큰
        String token =
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdXRoVG9rZW4iLCJlbWFpbCI6ImFkbWluQHNzYWZ5LmNvbSIsIm5hbWUiOiLqtIDrpqzsnpAiLCJyb2xlIjoiQURNSU4iLCJleHAiOjE3NDUwNzUwMzJ9.uhf2kgYx_Nj62AA_1qpCXmHEOiz0H49Q3tREcS7y5qo";
        // when & then
        Assertions.assertThrows(SignatureException.class, () -> util.getClaims(token));
    }
}
