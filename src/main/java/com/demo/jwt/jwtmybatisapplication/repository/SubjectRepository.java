package com.demo.jwt.jwtmybatisapplication.repository;

import com.demo.jwt.jwtmybatisapplication.config.SchoolManagementSQLConnMapper;
import com.demo.jwt.jwtmybatisapplication.model.SubjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@SchoolManagementSQLConnMapper("SubjectMapper")
public interface SubjectRepository {

    SubjectEntity findSubjectById(long id);
    List<SubjectEntity> findAll();
    void saveAll(List<SubjectEntity> subjects);
}
