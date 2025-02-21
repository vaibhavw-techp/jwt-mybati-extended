package com.demo.jwt.JwtMybatisApplication.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SchoolManagementSQLConnMapper {
    String value() default "";
}
