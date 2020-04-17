package com.apple.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidExpressionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public InvalidExpressionException(String message) {
        super(message);
        this.message = message;
    }

	public String getMessage() {
		return message;
	}
	
}
