package com.demo.jwt.JwtMybatisApplication.dto;

import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import lombok.Data;

import java.util.List;

@Data
public class StudentDisplayAsSubjects {
    private String name;
    private List<SubjectDisplayDto> subjects;
}
