package com.demo.jwt.jwtmybatisapplication.repository.mgrepository;

import com.demo.jwt.jwtmybatisapplication.config.MessManagementSQLConnMapper;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.MessEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@MessManagementSQLConnMapper("MessMapper")
public interface MessRepository {

    void save(MessEntity mess);

    List<MessEntity> findAll();
    MessEntity findMessById(Long id);

}

