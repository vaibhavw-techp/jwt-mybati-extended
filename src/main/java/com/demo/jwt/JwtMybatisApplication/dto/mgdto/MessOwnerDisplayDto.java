package com.demo.jwt.JwtMybatisApplication.dto.mgdto;

import lombok.Data;

@Data
public class MessOwnerDisplayDto {
    private Long id;
    private String name;
    private String contactNumber;
    private Long messId;
}
