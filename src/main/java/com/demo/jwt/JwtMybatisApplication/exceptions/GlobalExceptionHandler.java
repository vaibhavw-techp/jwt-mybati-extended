package com.demo.jwt.JwtMybatisApplication.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        return new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorDetails duplicateResourceException(DuplicateResourceException ex, WebRequest request){
        return new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDetails handleUnauthorizedException(UnauthorizedAccessException ex, WebRequest request) {
        return new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
