package com.demo.jwt.jwtmybatisapplication.repository.mgrepository;

import com.demo.jwt.jwtmybatisapplication.config.MessManagementSQLConnMapper;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.MessOwnerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@MessManagementSQLConnMapper("MessOwner")
public interface MessOwnerRepository {

    void save(MessOwnerEntity messOwner);

    List<MessOwnerEntity> findOwnerByMessId(Long id);
    List<MessOwnerEntity> findAll();

}
