package com.demo.jwt.JwtMybatisApplication.repository.mgrepository;

import com.demo.jwt.JwtMybatisApplication.config.MessManagementSQLConnMapper;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@MessManagementSQLConnMapper("MessMapper")
public interface MessRepository {

    void save(MessEntity mess);

    List<MessEntity> findAll();
    MessEntity findMessById(Long id);

}

