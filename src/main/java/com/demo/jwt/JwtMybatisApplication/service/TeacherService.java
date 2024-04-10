package com.demo.jwt.JwtMybatisApplication.service;

import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.mapstruct.TeacherMapper;
import com.demo.jwt.JwtMybatisApplication.model.TeacherEntity;
import com.demo.jwt.JwtMybatisApplication.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherMapper teacherMapper;

    public TeacherDisplayDto getTeacherById(@PathVariable long id){
           return teacherMapper.teacherEntityToTeacherDisplayDto(teacherRepository.findTeacherById(id));
    }
    public List<TeacherDisplayInfoDto> getAllTeachers(){
        List<TeacherDisplayInfoDto> teacherDisplayWithIdDtos = teacherMapper.teacherDisplayInfoDtosFromEntities(teacherRepository.findAll());
        return teacherDisplayWithIdDtos;
    }

    public TeacherSubjectDisplayDto getTeacherWithSubjectsById(Long id) {
           return teacherMapper.teacherEntityToTeacherSubjectDisplayDto(teacherRepository.findAllByTeacher(id));
    }

    public TeacherEntity addTeacher(TeacherAdditionDto teacher){
        if (teacher.getName() == null || teacher.getName().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty");
        }

        TeacherEntity teacherEntity = teacherMapper.teacherAddDtoToTeacherEntity(teacher);
        teacherRepository.save(teacherEntity);
        return teacherEntity;
    }

}
