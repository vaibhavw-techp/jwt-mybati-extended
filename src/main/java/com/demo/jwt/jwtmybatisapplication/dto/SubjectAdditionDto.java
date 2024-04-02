package com.demo.jwt.jwtmybatisapplication.dto;

import lombok.Data;

@Data
public class SubjectAdditionDto {
    private String name;
    private TeacherSubjectDto teacher;
}
