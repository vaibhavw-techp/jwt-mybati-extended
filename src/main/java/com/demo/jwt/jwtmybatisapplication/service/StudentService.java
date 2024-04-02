package com.demo.jwt.jwtmybatisapplication.service;

import com.demo.jwt.jwtmybatisapplication.dto.StudentAddDto;
import com.demo.jwt.jwtmybatisapplication.dto.StudentDisplayAsSubjects;
import com.demo.jwt.jwtmybatisapplication.dto.StudentDisplayByIdDto;
import com.demo.jwt.jwtmybatisapplication.dto.StudentsDisplayDto;
import com.demo.jwt.jwtmybatisapplication.mapstruct.StudentMapper;
import com.demo.jwt.jwtmybatisapplication.model.StudentEntity;
import com.demo.jwt.jwtmybatisapplication.model.SubjectEntity;
import com.demo.jwt.jwtmybatisapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public StudentDisplayByIdDto getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    public void assignSubjectsToStudent(Long studentId, List<SubjectEntity> subjects) {
        studentRepository.saveAllSubjectsForStudent(studentId, subjects);
    }

    public StudentDisplayByIdDto addStudent(StudentAddDto student) {
        StudentEntity studentEntity = studentMapper.studentAddDtoToEntity(student);
        studentRepository.save(studentEntity);
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    public StudentDisplayAsSubjects getStudentWithSubjects(Long studentId) {
        StudentEntity studentEntity = studentRepository.findBySubjects(studentId);
        return studentMapper.studentEntityToDisplayAsSubjects(studentEntity);
    }

    public List<StudentsDisplayDto> getAllStudentsWithFilters(String name, Integer age, String email) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("name", name);
        filters.put("age", age);
        filters.put("email", email);
        List<StudentEntity> studentEntities = studentRepository.findAll(filters);
        return studentMapper.studentEntitiesToDisplayDtos(studentEntities);
    }
}
