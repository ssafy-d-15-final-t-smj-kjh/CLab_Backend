package com.clab.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {
	private Integer userId;
	private String id;
	private String password;
	private String username;
	private String email;
	private String image;
	private String refreshToken;
}
