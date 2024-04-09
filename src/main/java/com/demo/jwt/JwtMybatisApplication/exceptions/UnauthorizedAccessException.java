package com.demo.jwt.JwtMybatisApplication.exceptions;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String resourceName) {
        super("You are not authorized to access this " + resourceName +" resource");
    }

    public UnauthorizedAccessException() {
        super("You are not authorized to access this resource");
    }
}
