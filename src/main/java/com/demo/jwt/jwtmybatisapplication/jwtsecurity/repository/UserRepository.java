package com.demo.jwt.jwtmybatisapplication.jwtsecurity.repository;

import com.demo.jwt.jwtmybatisapplication.config.usermanagementdatasource.UserManagementSQLConnMapper;
import com.demo.jwt.jwtmybatisapplication.jwtsecurity.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@UserManagementSQLConnMapper("UserMapper")
public interface UserRepository {
    UserEntity findUserByUsername(String username);
}
