package com.clab.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
	SELECT_SUCCESS(200, "SUC-SEL-200", "조회에 성공하였습니다."),
	DELETE_SUCCESS(200, "SUC-DEL-200", "삭제에 성공하였습니다."),
	INSERT_SUCCESS(201, "SUC-INS-201", "등록에 성공하였습니다."),
	UPDATE_SUCCESS(204, "SUC-UPD-204", "수정에 성공하였습니다.");
	
	private final int status;
	private final String code;
	private final String message;    
}
