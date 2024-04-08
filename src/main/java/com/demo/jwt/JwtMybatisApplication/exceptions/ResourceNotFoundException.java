package com.demo.jwt.JwtMybatisApplication.exceptions;


import java.util.List;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(Long id, String resourceName){
        super(resourceName+ " not found with id: "+id);
    }

    public ResourceNotFoundException(List<Long> ids, String resourceName) {
        super(resourceName + " not found with ids: "+ ids);
    }

    public ResourceNotFoundException(String resourceName) {
        super(resourceName + " not found!!");
    }
}
