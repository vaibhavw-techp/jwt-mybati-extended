package com.demo.jwt.JwtMybatisApplication.service;


import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.exceptions.ResourceNotFoundException;
import com.demo.jwt.JwtMybatisApplication.exceptions.UnauthorizedAccessException;
import com.demo.jwt.JwtMybatisApplication.mapstruct.StudentMapper;
import com.demo.jwt.JwtMybatisApplication.model.StudentEntity;
import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import com.demo.jwt.JwtMybatisApplication.repository.StudentRepository;
import com.demo.jwt.JwtMybatisApplication.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.*;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SubjectRepository subjectRepository;


    public StudentDisplayByIdDto getStudentById(Long id, Principal principal) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        if(studentEntity == null) throw new ResourceNotFoundException(id, "Student");

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) principal;
        Jwt jwt = jwtAuthenticationToken.getToken();
        String role = (String) jwt.getClaims().get("Role");

        if (role.equals("ROLE_STUDENT")) {
            Long studentIdFromToken = Long.parseLong(jwt.getClaims().get("assc_id").toString());
            if (id == studentIdFromToken) {
                return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
            } else {
                throw new UnauthorizedAccessException("student");
            }
        } else {
            return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
        }
    }

    public void assignSubjectsToStudent(Long studentId, List<SubjectEntity> subjects) {
        studentRepository.saveAllSubjectsForStudent(studentId, subjects);
    }

    public StudentDisplayByIdDto addStudent(StudentAddDto student){
        StudentEntity studentEntity = studentMapper.studentAddDtoToEntity(student);
        studentRepository.save(studentEntity);
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    public StudentDisplayAsSubjects getStudentWithSubjects(Long id, Principal principal) {
        StudentEntity studentEntity = studentRepository.findBySubjects(id);

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) principal;
        Jwt jwt = jwtAuthenticationToken.getToken();
        String role = (String) jwt.getClaims().get("Role");



        if (role.equals("ROLE_STUDENT")) {
            Long studentIdFromToken = Long.parseLong(jwt.getClaims().get("assc_id").toString());
            if (id == studentIdFromToken) {
                return studentMapper.studentEntityToDisplayAsSubjects(studentEntity);
            } else {
                throw new UnauthorizedAccessException("student");
            }
        } else {
            return studentMapper.studentEntityToDisplayAsSubjects(studentEntity);
        }

    }

    public List<StudentDisplayDto> getAllStudentsWithFilters(String name, Integer age, String email) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("name", name);
        filters.put("age", age);
        filters.put("email",email);
        List<StudentEntity> studentEntities = studentRepository.findAll(filters);
        return studentMapper.studentEntitiesToDisplayDtos(studentEntities);
    }

}
