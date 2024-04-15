package com.demo.jwt.JwtMybatisApplication.exceptions;

public class ManyRequestsException extends RuntimeException {
        public ManyRequestsException(){
            super("API request limit has been exhausted.");
        }

}

