package com.demo.jwt.JwtMybatisApplication.service;


import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.dto.log.AckDto;
import com.demo.jwt.JwtMybatisApplication.dto.log.StudentEventLogDto;
import com.demo.jwt.JwtMybatisApplication.exceptions.ResourceNotFoundException;
import com.demo.jwt.JwtMybatisApplication.mapstruct.StudentMapper;
import com.demo.jwt.JwtMybatisApplication.model.StudentEntity;
import com.demo.jwt.JwtMybatisApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.scheduling.config.FixedDelayTask;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private KafkaTemplate<String, AckDto> kafkaTemplate;

    @Value("${topic.log}")
    private String LOG_TOPIC;


    public StudentDisplayByIdDto getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        if(studentEntity == null) throw new ResourceNotFoundException(id, "Student");
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    private int x = 0;

    @RetryableTopic(
            attempts = "10",
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 20000)
    )
    @KafkaListener(topics = "student", groupId = "group-1", containerFactory = "studentListener")
    public void addStudent(StudentEventLogDto student) {

        if(student.getAge() < 20) {
            throw new RuntimeException("Exception Occured while processing!!");
        }

        try {
            System.out.println(x);
            System.out.println(student);
            StudentEntity studentEntity = studentMapper.mapStudentEventLogDtoToStudentEntity(student);
            studentRepository.save(studentEntity);
            handleException(student, "SAVED");
        } catch (Exception ex) {
            handleException(student, "FAILED");
        }
    }

    private void handleException(StudentEventLogDto student, String currentState) {
        AckDto ackDto = new AckDto(student.getLogId(), student.getName(), currentState);
        kafkaTemplate.send(LOG_TOPIC, ackDto);
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
