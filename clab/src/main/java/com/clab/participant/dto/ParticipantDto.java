package com.clab.participant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ParticipantDto {
	private Integer id;
	private Integer chatId;
	private String name;
	private Integer count;
	private Integer score;
	private Integer averageReplyTime;
	private Long chatLength;
	private String comment;
	private String alias;
	private Integer personaId;
}
