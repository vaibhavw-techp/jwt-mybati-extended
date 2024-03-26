package com.demo.jwt.JwtMybatisApplication.dto.mgdto;

import lombok.Data;

@Data
public class MessOwnerAdditionDto {
    private String name;
    private String contactNumber;
    private Long messId;
}
