package com.demo.jwt.JwtMybatisApplication.dto;

import lombok.Data;


@Data
public class StudentsDisplayDto {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
