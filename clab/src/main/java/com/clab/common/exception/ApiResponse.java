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
    
    public ApiResponse(SuccessCode sc, T data) {
    	this.status = sc.getStatus();
    	this.code = sc.getCode();
    	this.message = sc.getMessage();
    	this.data = data;
    }
}
