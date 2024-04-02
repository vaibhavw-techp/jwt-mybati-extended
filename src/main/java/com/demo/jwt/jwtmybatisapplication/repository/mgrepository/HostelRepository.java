package com.demo.jwt.jwtmybatisapplication.repository.mgrepository;

import com.demo.jwt.jwtmybatisapplication.config.MessManagementSQLConnMapper;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.HostelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@MessManagementSQLConnMapper("HostelMapper")
public interface HostelRepository {

    void save(HostelEntity hostel);

    List<HostelEntity> findAll();
}
