package com.clab.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseCode {
	// Common Error --- BEGIN
	// 잘못된 서버 요청
	BAD_REQUEST_ERROR(400, "G-001", "Bad Request Exception"),

	// @RequestBody 데이터 미 존재
	REQUEST_BODY_MISSING_ERROR(400, "G-002", "Required request body is missing"),

	// 유효하지 않은 타입
	INVALID_TYPE_VALUE(400, "G-003", " Invalid Type Value"),

	// Request Parameter 로 데이터가 전달되지 않을 경우
	MISSING_REQUEST_PARAMETER_ERROR(400, "G-004", "Missing Servlet RequestParameter Exception"),

	// 입력/출력 값이 유효하지 않음
	IO_ERROR(400, "G-005", "I/O Exception"),

	// com.google.gson JSON 파싱 실패
	JSON_PARSE_ERROR(400, "G-006", "JsonParseException"),

	// com.fasterxml.jackson.core Processing Error
	JACKSON_PROCESS_ERROR(400, "G-007", "com.fasterxml.jackson.core Exception"),

	// 권한이 없음
	FORBIDDEN_ERROR(403, "G-008", "Forbidden Exception"),

	// 서버로 요청한 리소스가 존재하지 않음
	NOT_FOUND_ERROR(404, "G-009", "Not Found Exception"),

	// NULL Point Exception 발생
	NULL_POINT_ERROR(404, "G-010", "Null Point Exception"),

	// @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
	NOT_VALID_ERROR(404, "G-011", "handle Validation Exception"),

	// @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
	NOT_VALID_HEADER_ERROR(404, "G-012", "Header에 데이터가 존재하지 않는 경우 "),

	// 서버가 처리 할 방법을 모르는 경우 발생
	INTERNAL_SERVER_ERROR(500, "G-999", "Internal Server Error Exception"),
	// Common Error --- END

	
	// Chat Error --- BEGIN
	CHAT_NOT_FOUND(404, "CHAT-001", "대화를 조회할 수 없습니다."),
	CHAT_BAD_REQUEST(400, "CHAT-002", "잘못된 접근입니다. DB에 수정이 일어나지 않았습니다."),
	// Chat Error --- END
	
	// Category Error --- BEGIN
	
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

