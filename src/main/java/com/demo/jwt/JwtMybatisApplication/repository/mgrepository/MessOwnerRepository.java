package com.demo.jwt.JwtMybatisApplication.repository.mgrepository;

import com.demo.jwt.JwtMybatisApplication.config.MessManagementSQLConnMapper;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessOwnerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@MessManagementSQLConnMapper("MessOwner")
public interface MessOwnerRepository {

    void save(MessOwnerEntity messOwner);

    List<MessOwnerEntity> findOwnerByMessId(Long id);
    List<MessOwnerEntity> findAll();

}
