package com.clab.member.dto;

import lombok.Data;

@Data
public class MemberDto {
	private Integer userId;
	private String id;
	private String password;
	private String email;
	private String image;
}
