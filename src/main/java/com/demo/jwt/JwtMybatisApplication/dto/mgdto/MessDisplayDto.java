package com.demo.jwt.JwtMybatisApplication.dto.mgdto;

import lombok.Data;

@Data
public class MessDisplayDto {
    private Long id;
    private String name;
    private int capacity;
    private String location;
}
