package com.demo.jwt.JwtMybatisApplication.dto.mgdto;

import lombok.Data;

@Data
public class HostelDisplayDto {
    private Long id;
    private String name;
    private String address;
    private int capacity;
    private int numRooms;
}
