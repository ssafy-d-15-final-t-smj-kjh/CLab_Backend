package com.clab.content.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContentDto {
	private Integer id;
	private Integer participantId;
	private String content;
	private LocalDateTime time;

}
