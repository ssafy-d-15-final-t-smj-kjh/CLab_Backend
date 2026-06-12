package com.clab.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {
	SELECT_SUCCESS(200, "SUCCESS-SEL", "조회에 성공하였습니다."),
	DELETE_SUCCESS(204, "SUCCESS-DEL", "삭제에 성공하였습니다."),
	INSERT_SUCCESS(201, "SUCCESS-INS", "등록에 성공하였습니다."),
	UPDATE_SUCCESS(200, "SUCCESS-UPD", "수정에 성공하였습니다.");
	
	private final int status;
	private final String code;
	private final String message;    
}
