package com.demo.jwt.JwtMybatisApplication.exceptions;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String resourceName){
        super(resourceName + " already assigned !!");
    }
}
