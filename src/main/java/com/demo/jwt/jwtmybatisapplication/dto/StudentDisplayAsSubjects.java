package com.demo.jwt.jwtmybatisapplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDisplayAsSubjects {
    private String name;
    private List<SubjectDisplayDto> subjects;
}
