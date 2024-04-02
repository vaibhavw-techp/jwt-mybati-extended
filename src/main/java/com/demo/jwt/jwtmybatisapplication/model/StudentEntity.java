package com.demo.jwt.jwtmybatisapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private List<SubjectEntity> subjects;
}
