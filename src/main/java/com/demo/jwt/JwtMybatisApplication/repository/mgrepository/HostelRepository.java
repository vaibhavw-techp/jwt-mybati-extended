package com.demo.jwt.JwtMybatisApplication.repository.mgrepository;

import com.demo.jwt.JwtMybatisApplication.config.MessManagementSQLConnMapper;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.HostelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@MessManagementSQLConnMapper("HostelMapper")
public interface HostelRepository {

    void save(HostelEntity hostel);

    List<HostelEntity> findAll();
}
