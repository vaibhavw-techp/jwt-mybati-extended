package com.demo.jwt.JwtMybatisApplication.config.jwt;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BypassRateLimit {

}

