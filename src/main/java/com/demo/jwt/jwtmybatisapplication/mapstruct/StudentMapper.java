package com.demo.jwt.jwtmybatisapplication.mapstruct;

import com.demo.jwt.jwtmybatisapplication.dto.StudentAddDto;
import com.demo.jwt.jwtmybatisapplication.dto.StudentDisplayAsSubjects;
import com.demo.jwt.jwtmybatisapplication.dto.StudentDisplayByIdDto;
import com.demo.jwt.jwtmybatisapplication.dto.StudentsDisplayDto;
import com.demo.jwt.jwtmybatisapplication.model.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = SubjectMapper.class)
public interface StudentMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "age", target = "age")
    StudentEntity studentAddDtoToEntity(StudentAddDto dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "subjects", target = "subjects")
    StudentDisplayAsSubjects studentEntityToDisplayAsSubjects(StudentEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "age", target = "age")
    StudentDisplayByIdDto studentEntityToDisplayByIdDto(StudentEntity entity);

    List<StudentsDisplayDto> studentEntitiesToDisplayDtos(List<StudentEntity> entities);
}
