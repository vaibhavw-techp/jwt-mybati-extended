package com.demo.jwt.JwtMybatisApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

@Data
public class StudentAddDto {
    private Long id;
    @NotNull
    private String name;
    @Email
    private String email;
    @Positive
    private Integer age;
}
