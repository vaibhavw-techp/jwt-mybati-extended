package com.demo.jwt.JwtMybatisApplication.dto;

import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import lombok.Data;

import java.util.List;

@Data
public class TeacherAdditionDto {
    private String name;
    private int age;
    private String email;
}
