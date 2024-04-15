package com.demo.jwt.JwtMybatisApplication.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


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

    @ExceptionHandler(ManyRequestsException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ErrorDetails handlerTooManyRequestsException(ManyRequestsException ex, WebRequest request) {
        return new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    }

}
