package com.demo.jwt.jwtmybatisapplication.dto;

import lombok.Data;

@Data
public class SubjectEntityDto {
    private Long id;
    private String name;
    private TeacherSubjectDto teacher;
}
