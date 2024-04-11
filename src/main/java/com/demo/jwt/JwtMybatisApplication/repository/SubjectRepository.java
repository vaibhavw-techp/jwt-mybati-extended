package com.demo.jwt.JwtMybatisApplication.repository;

import com.demo.jwt.JwtMybatisApplication.config.SchoolManagementSQLConnMapper;
import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@SchoolManagementSQLConnMapper("SubjectMapper")
public interface SubjectRepository {

    SubjectEntity findSubjectById(long id);
    List<SubjectEntity> findAll();
    void saveAll(List<SubjectEntity> subjects);
    Long findSubjectIdByName(String subjectName);
}
