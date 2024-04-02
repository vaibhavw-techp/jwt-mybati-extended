package com.demo.jwt.jwtmybatisapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectEntity {
    private Long id;
    private String name;
    private TeacherEntity teacher;
}
