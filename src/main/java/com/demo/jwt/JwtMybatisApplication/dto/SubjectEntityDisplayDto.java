package com.demo.jwt.JwtMybatisApplication.dto;

import lombok.Data;

@Data
public class SubjectEntityDisplayDto {
    private Long id;
    private String name;
    private TeacherSubjectDisplayDto teacher;
}
