package com.demo.jwt.JwtMybatisApplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDisplaySubjectsDto {
    private String name;
    private List<SubjectDisplayDto> subjects;
}
