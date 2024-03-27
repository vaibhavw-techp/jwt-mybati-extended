package com.demo.jwt.JwtMybatisApplication.config.usermanagementdatasource;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UserManagementSQLConnMapper {
    String value() default "";
}


