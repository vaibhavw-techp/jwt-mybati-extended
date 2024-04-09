package com.demo.jwt.JwtMybatisApplication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({"com.demo.jwt.JwtMybatisApplication.repository","com.demo.jwt.JwtMybatisApplication.repository.mgrepository"})
public class JwtMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtMybatisApplication.class, args);
	}

}
