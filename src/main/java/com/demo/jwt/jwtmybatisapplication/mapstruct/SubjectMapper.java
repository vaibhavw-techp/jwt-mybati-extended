package com.demo.jwt.jwtmybatisapplication.mapstruct;

import com.demo.jwt.jwtmybatisapplication.dto.SubjectAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.SubjectDisplayDto;
import com.demo.jwt.jwtmybatisapplication.dto.SubjectEntityDisplayDto;
import com.demo.jwt.jwtmybatisapplication.model.SubjectEntity;
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
