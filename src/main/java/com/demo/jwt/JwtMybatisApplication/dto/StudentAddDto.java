package com.demo.jwt.JwtMybatisApplication.dto;

import lombok.Data;

@Data
public class StudentAddDto {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
