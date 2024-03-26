package com.demo.jwt.JwtMybatisApplication.dto;

import com.demo.jwt.JwtMybatisApplication.model.TeacherEntity;
import lombok.Data;

@Data
public class SubjectAdditionDto {
    private String name;
    private TeacherSubjectDto teacher;
}
