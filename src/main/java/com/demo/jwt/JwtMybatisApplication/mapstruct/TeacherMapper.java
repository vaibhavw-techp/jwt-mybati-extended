package com.demo.jwt.JwtMybatisApplication.mapstruct;

import com.demo.jwt.JwtMybatisApplication.dto.TeacherAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherSubjectDisplayDto;
import com.demo.jwt.JwtMybatisApplication.model.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = SubjectMapper.class)
public interface TeacherMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "email", target = "email")
    TeacherDisplayDto teacherEntityToTeacherDisplayDto(TeacherEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "email", target = "email")
    TeacherDisplayInfoDto teacherEntityToTeacherDisplayInfoDto(TeacherEntity teacherEntity);

    @Mapping(source = "teacherAddDto.name", target = "name")
    @Mapping(source = "teacherAddDto.age", target = "age")
    @Mapping(source = "teacherAddDto.email", target = "email")
    TeacherEntity teacherAddDtoToTeacherEntity(TeacherAdditionDto teacherAddDto);

    List<TeacherDisplayInfoDto> teacherDisplayInfoDtosFromEntities(List<TeacherEntity> teacherEntities);

    TeacherSubjectDisplayDto teacherEntityToTeacherSubjectDisplayDto(TeacherEntity teacher);
}
