package com.demo.jwt.jwtmybatisapplication.dto;

import lombok.Data;

@Data
public class SubjectEntityDisplayDto {
    private Long id;
    private String name;
    private TeacherSubjectDisplayDto teacher;
}
