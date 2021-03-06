package com.apple.calculator.exception;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class CalculatorExceptionHandler extends ResponseEntityExceptionHandler {
   
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Invalid expression", details);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidExpressionException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(InvalidExpressionException ex, WebRequest wr){
      return ((ResponseEntity.BodyBuilder)ResponseEntity.notFound()).body(new ErrorResponse(ex.getMessage()));
    }
}