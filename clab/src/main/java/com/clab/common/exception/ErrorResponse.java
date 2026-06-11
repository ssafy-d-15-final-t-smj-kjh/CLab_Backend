package com.clab.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse<T> {
	private int status;
	private String code;
	private String message;
	private T data;
	
	public ErrorResponse(ErrorCode ec, T data) {
    	this.status = ec.getStatus();
    	this.code = ec.getCode();
    	this.message = ec.getMessage();
    	this.data = data;
    }
}
