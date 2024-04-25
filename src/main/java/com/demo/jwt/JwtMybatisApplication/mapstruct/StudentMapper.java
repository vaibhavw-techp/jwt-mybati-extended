package com.demo.jwt.JwtMybatisApplication.mapstruct;

import com.demo.jwt.JwtMybatisApplication.dto.StudentAddDto;
import com.demo.jwt.JwtMybatisApplication.dto.StudentDisplayByIdDto;
import com.demo.jwt.JwtMybatisApplication.dto.StudentDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.StudentDisplaySubjectsDto;
import com.demo.jwt.JwtMybatisApplication.model.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = SubjectMapper.class)
public interface StudentMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "age", target = "age")
    StudentEntity studentAddDtoToEntity(StudentAddDto dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "subjects", target = "subjects")
    StudentDisplaySubjectsDto studentEntityToDisplayAsSubjects(StudentEntity student);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "age", target = "age")
    StudentDisplayByIdDto studentEntityToDisplayByIdDto(StudentEntity entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "age", target = "age")
    StudentDisplayDto mapStudentEntityToStudentDisplayDto(StudentEntity student);

    List<StudentDisplayDto> studentEntitiesToDisplayDtos(List<StudentEntity> entities);
}
