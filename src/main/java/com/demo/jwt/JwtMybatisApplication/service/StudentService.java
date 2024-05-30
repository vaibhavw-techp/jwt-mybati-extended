package com.demo.jwt.JwtMybatisApplication.service;


import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.dto.log.AckDto;
import com.demo.jwt.JwtMybatisApplication.dto.log.StudentEventLogDto;
import com.demo.jwt.JwtMybatisApplication.exceptions.ResourceNotFoundException;
import com.demo.jwt.JwtMybatisApplication.mapstruct.StudentMapper;
import com.demo.jwt.JwtMybatisApplication.model.StudentEntity;
import com.demo.jwt.JwtMybatisApplication.repository.StudentRepository;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
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

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentDisplayByIdDto getStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        if(studentEntity == null) throw new ResourceNotFoundException(id, "Student");
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 2000),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            retryTopicSuffix = "-custom-try",
            dltTopicSuffix = "-dead-t"
    )
    @KafkaListener(topics = "student", groupId = "group-1", containerFactory = "studentListener")
    public void addStudentAndSendAckToProducer(StudentEventLogDto student) {
        try {
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

    @DltHandler
    private void listenDLTForStudentAddition(StudentEventLogDto student) {
        logger.info(String.valueOf(student));
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
