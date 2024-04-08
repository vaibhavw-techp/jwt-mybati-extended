package com.demo.jwt.JwtMybatisApplication.service;


import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.exceptions.DuplicateResourceException;
import com.demo.jwt.JwtMybatisApplication.exceptions.ResourceNotFoundException;
import com.demo.jwt.JwtMybatisApplication.mapstruct.StudentMapper;
import com.demo.jwt.JwtMybatisApplication.model.StudentEntity;
import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import com.demo.jwt.JwtMybatisApplication.repository.StudentRepository;
import com.demo.jwt.JwtMybatisApplication.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SubjectRepository subjectRepository;

    public StudentDisplayByIdDto getStudentById(Long id) throws ResourceNotFoundException{
        StudentEntity studentEntity = studentRepository.findStudentById(id);
        if(studentEntity == null) throw new ResourceNotFoundException(id, "Student");
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    public StudentDisplayByIdDto addStudent(StudentAddDto student){
        StudentEntity studentEntity = studentMapper.studentAddDtoToEntity(student);
        studentRepository.save(studentEntity);
        return studentMapper.studentEntityToDisplayByIdDto(studentEntity);
    }

    public StudentDisplaySubjectsDto getStudentWithSubjects(Long studentId) {
        StudentEntity studentEntity = studentRepository.findBySubjects(studentId);
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

    public void assignSubjectToStudent(Long studentId, Long subjectId) throws DuplicateResourceException {
        // Get assigned subject IDs
        List<Long> assignedSubjectIds = studentRepository.findSubjectsByStudentId(studentId)
                .stream()
                .map(SubjectEntity::getId)
                .collect(Collectors.toList());

        // Check if the subject is already assigned
        if (assignedSubjectIds.contains(subjectId)) {
            throw new DuplicateResourceException("Subject");
        }
        List<Long> subjectIds = Collections.singletonList(subjectId);
        // Update the subject to the student
        studentRepository.updateSubjectsToStudent(studentId, subjectIds);
    }

    @Transactional(transactionManager = "schoolManagement", rollbackFor = {DuplicateResourceException.class, ResourceNotFoundException.class})
    public List<StudentDisplaySubjectsDto> assignSubjectSToStudentsByName(SubjectAssignDto subjectAssignDtos) throws DuplicateResourceException,
            ResourceNotFoundException {

        List<StudentEntity> students = studentRepository.findAllStudents();

        for(String subject: subjectAssignDtos.getSubjects()) {
            Long subjectId = subjectRepository.findSubjectIdByName(subject);

            // Checked Exception Handling for Subject Exist and not exists
            if(subjectId == null) throw new ResourceNotFoundException("Subject");

            for(StudentEntity student: students) {
                assignSubjectToStudent(student.getId(), subjectId);
            }
        }

        List<StudentDisplaySubjectsDto> retStudents = studentMapper.mapStudentEntitiesToStudentDisplayWithSubjects(studentRepository.findAllStudents());
        return retStudents;
    }
}

