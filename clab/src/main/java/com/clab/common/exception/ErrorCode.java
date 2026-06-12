package com.clab.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseCode {
	// Common Error --- BEGIN
	INVALID_REQUEST(400, "ERR-REQ-400", "잘못된 요청입니다."),
    INVALID_JSON(400, "ERR-JSON-400", "요청 본문 형식이 올바르지 않습니다."),
    UNAUTHORIZED(401, "ERR-AUTH-401", "인증이 필요합니다."),
    FORBIDDEN(403, "ERR-FORBIDDEN-403", "접근 권한이 없습니다."),
    NOT_FOUND(404, "ERR-NOTFOUND-404", "대상을 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "ERR-SERVER-500", "서버 내부 오류가 발생했습니다."),
	// Common Error --- END

	
	// Chat Error --- BEGIN
	CHAT_NOT_FOUND(404, "CHAT-001", "대화를 조회할 수 없습니다."),
	CHAT_BAD_REQUEST(400, "CHAT-002", "잘못된 접근입니다. DB에 수정이 일어나지 않았습니다."),
	// Chat Error --- END
	
	// Category Error --- BEGIN
	CATEGORY_NOT_FOUND(404, "CATE-001", "카테고리를 조회할 수 없습니다."),
	CATEGORY_BAD_REQUEST(400, "CATE-002", "잘못된 접근입니다. 카테고리 DB에 적용이 되지 않았습니다."),
	CATEGORY_INSERT_FAILED(400, "CATE-003", "카테고리 생성에 실패하였습니다."),
	CATEGORY_UPDATE_FAILED(400, "CATE-004", "카테고리 수정에 실패하였습니다."),
	CATEGORY_DELETE_FAILED(400, "CATE-005", "카테고리 삭제에 실패하였습니다."),
	// Category Error --- END
	
	// Common Error --- BEGIN
	
	// Common Error --- END
	
	// Content Error --- BEGIN
	
	// Content Error --- END
	
	// Content-Category Error --- BEGIN
	
	// Content-Category Error --- END
	
	// Member Error --- BEGIN
	
	// Member Error --- END
	
	// Participant Error --- BEGIN
	
	// Participant Error --- END
	
	// Participant-Category Error --- BEGIN
	
	// Participant-Category Error --- END
	
	
	
	; // End
	

	private final int status;
	private final String code;
	private final String message;
}

