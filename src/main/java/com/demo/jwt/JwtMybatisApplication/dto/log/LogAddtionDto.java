package com.demo.jwt.JwtMybatisApplication.dto.log;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAddtionDto {
    @NotNull
    private String name;
    @Email
    private String email;
    private int statusCode;
    @NotNull
    private LocalDateTime timestamp;
    @NotNull
    private String statusMessage;
    @NotNull
    private String source;
}
