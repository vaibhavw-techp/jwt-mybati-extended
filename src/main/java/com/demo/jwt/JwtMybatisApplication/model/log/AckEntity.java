package com.demo.jwt.JwtMybatisApplication.model.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AckEntity {
    private Long logId;
    private String name;
    private String currentState;
}

