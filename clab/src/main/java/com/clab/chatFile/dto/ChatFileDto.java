package com.clab.chatFile.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatFileDto {
	private Integer id;
	private String originalFileName;
	private String saveFileName;
	private String source;
	private Integer fileSize;
	private LocalDateTime createdAt;
}
