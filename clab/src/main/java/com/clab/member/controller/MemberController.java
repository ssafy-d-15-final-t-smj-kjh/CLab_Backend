package com.clab.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clab.member.dto.MemberDto;
import com.clab.member.service.MemberService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "MemberController", description = "회원 관리 API")
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping
	public List<MemberDto> findAll() {
		return memberService.findAll();
	}
	
	@GetMapping("/{id}")
	public MemberDto findById(int id) {
		return memberService.findById(id);
	}
	
	@PostMapping("/join")
	public void join(@RequestBody MemberDto dto) {
		memberService.insert(dto);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refresh(@RequestHeader("Refresh-Token") String refreshToken) {
		String accessToken = memberService.refresh(refreshToken);
		return ResponseEntity.ok(Map.of("accessToken", accessToken));
	}
	
	@PostMapping("/logout")
	public void logout(@RequestHeader("Refresh-Token") String refreshToken) {
		memberService.logout(refreshToken);
	}
	
	@PatchMapping("/{id}")
	public void update(@PathVariable("id") int id, @RequestBody MemberDto dto) {
		memberService.update(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		memberService.delete(id);
	}
}
