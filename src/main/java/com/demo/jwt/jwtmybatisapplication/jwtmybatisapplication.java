package com.demo.jwt.jwtmybatisapplication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({"com.demo.jwt.jwtmybatisapplication.repository", "com.demo.jwt.jwtmybatisapplication.repository.mgrepository",
  "com.demo.jwt.jwtmybatisapplication.jwtsecurity.repository"})

public class jwtmybatisapplication {

    public static void main(String[] args) {
        SpringApplication.run(jwtmybatisapplication.class, args);
    }
}
