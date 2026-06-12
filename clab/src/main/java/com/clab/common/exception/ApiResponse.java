package com.clab.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private int status;
    private String code;
    private String message;
    private T data;
    
    public ApiResponse(BaseCode c, T data) {
    	this.status = c.getStatus();
    	this.code = c.getCode();
    	this.message = c.getMessage();
    	this.data = data;
    }
}
