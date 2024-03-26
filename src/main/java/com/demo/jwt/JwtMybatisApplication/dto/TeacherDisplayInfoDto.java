package com.demo.jwt.JwtMybatisApplication.dto;

import lombok.Data;

@Data
public class TeacherDisplayInfoDto {
    private Long id;
    private String name;
    private int age;
    private String email;
}
