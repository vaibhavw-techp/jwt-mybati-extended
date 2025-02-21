package com.demo.jwt.JwtMybatisApplication.service;

import com.demo.jwt.JwtMybatisApplication.dto.SubjectAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.SubjectDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.SubjectEntityDisplayDto;
import com.demo.jwt.JwtMybatisApplication.mapstruct.SubjectMapper;
import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import com.demo.jwt.JwtMybatisApplication.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectMapper subjectMapper;

    public SubjectEntityDisplayDto getSubjectById(@PathVariable Long id){
        return subjectMapper.subjectEntityToSubjectDisplayDto(subjectRepository.findSubjectById(id));
    }

    public List<SubjectDisplayDto> getSubjects(){
        List<SubjectDisplayDto> subjects = subjectMapper.subjectEntitiesToSubjectDisplayDtos(subjectRepository.findAll());
        return subjects;
    }

    public List<SubjectDisplayDto> addSubjects( List<SubjectAdditionDto> subjects) {
        List<SubjectEntity> subjectEntities = subjectMapper.SubjectAddtionDtosToSubjectEntities(subjects);
        subjectRepository.saveAll(subjectEntities);
        return subjectMapper.subjectEntitiesToSubjectDisplayDtos(subjectEntities);
    }
}
