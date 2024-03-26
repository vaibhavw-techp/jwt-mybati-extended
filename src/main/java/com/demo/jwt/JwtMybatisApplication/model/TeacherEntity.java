package com.demo.jwt.JwtMybatisApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity {
    private Long id;
    private String name;
    private int age;
    private String email;
    private List<SubjectEntity> subjects;
}
