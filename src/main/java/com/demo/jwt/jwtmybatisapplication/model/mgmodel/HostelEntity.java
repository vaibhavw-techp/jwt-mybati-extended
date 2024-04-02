package com.demo.jwt.jwtmybatisapplication.model.mgmodel;

import lombok.Data;

@Data
public class HostelEntity {
    private Long id;
    private String name;
    private String address;
    private int capacity;
    private int numRooms;
}
