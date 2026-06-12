package com.clab.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(
            CustomException exception) {

        return ResponseEntity
                .status(exception.getErrorCode().getStatus())
                .body(new ApiResponse(exception.getErrorCode(), null));
    }

}
