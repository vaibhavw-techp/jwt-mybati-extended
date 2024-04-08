package com.demo.jwt.JwtMybatisApplication.exceptions;

public class DuplicateResourceException extends Exception {

    public DuplicateResourceException(String resourceName){
        super(resourceName + " already assigned !!");
    }
}
