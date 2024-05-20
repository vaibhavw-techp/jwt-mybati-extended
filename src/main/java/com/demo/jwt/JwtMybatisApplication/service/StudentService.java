package com.demo.jwt.JwtMybatisApplication.service;


import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.exceptions.ResourceNotFoundException;
import com.demo.jwt.JwtMybatisApplication.mapstruct.StudentMapper;
import com.demo.jwt.JwtMybatisApplication.model.StudentEntity;
import com.demo.jwt.JwtMybatisApplication.model.log.LogEntity;
import com.demo.jwt.JwtMybatisApplication.repository.StudentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private KafkaTemplate<String, LogEntity> kafkaTemplate;

    @Value("${topic.log}")
    private String LOG_TOPIC;


    public StudentDisplayByIdDto getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        if(studentEntity == null) throw new ResourceNotFoundException(id, "Student");
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    @KafkaListener(topics = "student", groupId = "group-1", containerFactory = "studentListener")
    public void addStudent(StudentAddDto student) {
        try {
            System.out.println(student);
            StudentEntity studentEntity = studentMapper.studentAddDtoToEntity(student);
            studentRepository.save(studentEntity);
            handleException(student, 200, "Student added successfully");
        } catch (ValidationException ex) {
            handleException(student, 400, ex.getMessage());
        } catch (Exception ex) {
            handleException(student, 500, "An unexpected exception occurred: " + ex.getMessage());
        }
    }

    private void handleException(StudentAddDto student, int statusCode, String statusMessage) {
        LogEntity logEntity = new LogEntity(student.getName(),student.getEmail(),statusCode,LocalDateTime.now(),statusMessage,"Consumer");
        kafkaTemplate.send(LOG_TOPIC, logEntity);
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
