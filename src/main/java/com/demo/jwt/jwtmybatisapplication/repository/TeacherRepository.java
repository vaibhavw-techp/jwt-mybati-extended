package com.demo.jwt.jwtmybatisapplication.repository;

import com.demo.jwt.jwtmybatisapplication.config.SchoolManagementSQLConnMapper;
import com.demo.jwt.jwtmybatisapplication.model.TeacherEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@SchoolManagementSQLConnMapper("TeacherMapper")
public interface TeacherRepository {

    TeacherEntity findTeacherById(long id);
    TeacherEntity findAllByTeacher(@Param("id") Long id);
    List<TeacherEntity> findAll();
    void save(TeacherEntity teacher);
}
