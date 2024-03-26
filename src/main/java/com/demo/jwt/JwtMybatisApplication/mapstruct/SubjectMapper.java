package com.demo.jwt.JwtMybatisApplication.mapstruct;

import com.demo.jwt.JwtMybatisApplication.dto.SubjectAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.SubjectDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.SubjectEntityDisplayDto;
import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "teacher", source = "dto.teacher")
    @Mapping(target = "name", source = "dto.name")
    SubjectEntity subjectAdditionDtoToSubjectEntity(SubjectAdditionDto dto);

    @Mapping(target = "id", source = "subjectEntity.id")
    @Mapping(target = "name", source = "subjectEntity.name")
    SubjectEntityDisplayDto subjectEntityToSubjectDisplayDto(SubjectEntity subjectEntity);

    List<SubjectDisplayDto> subjectEntitiesToSubjectDisplayDtos(List<SubjectEntity> subjectEntities);

    List<SubjectEntity> SubjectAddtionDtosToSubjectEntities(List<SubjectAdditionDto> subjects);

}
