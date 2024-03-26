package com.demo.jwt.JwtMybatisApplication.model.mgmodel;

import lombok.Data;

@Data
public class MessOwnerEntity {
    private Long id;
    private String name;
    private String contactNumber;
    private Long messId;
}
