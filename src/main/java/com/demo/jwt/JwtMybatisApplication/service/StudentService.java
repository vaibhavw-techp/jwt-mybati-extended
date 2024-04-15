package com.demo.jwt.JwtMybatisApplication.service;


import com.demo.jwt.JwtMybatisApplication.exceptions.ManyRequestsException;
import com.demo.jwt.JwtMybatisApplication.ratelimit.RateLimitingService;
import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.exceptions.ResourceNotFoundException;
import com.demo.jwt.JwtMybatisApplication.mapstruct.StudentMapper;
import com.demo.jwt.JwtMybatisApplication.model.StudentEntity;
import com.demo.jwt.JwtMybatisApplication.repository.StudentRepository;
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
    private RateLimitingService rateLimitingService;


    public StudentDisplayByIdDto getStudentById(Long id, Principal principal) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        if(studentEntity == null) throw new ResourceNotFoundException(id, "Student");

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) principal;
        Jwt jwt = jwtAuthenticationToken.getToken();
        String username = principal.getName();
        String plan = (String) jwt.getClaims().get("plan");

        try {
            if (rateLimitingService.tryConsume(username, plan)) {
                return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
            } else {
                throw new ManyRequestsException();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while processing rate limiting: " + e.getMessage());
        }
    }

    public StudentDisplayByIdDto addStudent(StudentAddDto student){
        StudentEntity studentEntity = studentMapper.studentAddDtoToEntity(student);
        studentRepository.save(studentEntity);
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    public StudentDisplaySubjectsDto getStudentWithSubjects(Long id) {
        StudentEntity studentEntity = studentRepository.findBySubjects(id);
        return studentMapper.studentEntityToDisplayAsSubjects(studentEntity);
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
