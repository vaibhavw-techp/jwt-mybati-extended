package com.demo.jwt.JwtMybatisApplication.jwtsecurity.repository;

import com.demo.jwt.JwtMybatisApplication.config.usermanagementdatasource.UserManagementSQLConnMapper;
import com.demo.jwt.JwtMybatisApplication.jwtsecurity.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@UserManagementSQLConnMapper("UserMapper")
public interface UserRepository {
    UserEntity findUserByUsername(String username);
}
