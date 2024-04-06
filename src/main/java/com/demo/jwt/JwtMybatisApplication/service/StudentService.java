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

    public StudentDisplayAsSubjects getStudentWithSubjects(Long studentId) {
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

    public void assignSubjectsToStudent(Long studentId, List<Long> subjectIds) throws ResourceNotFoundException,
            DuplicateResourceException {

        // Get assigned subject IDs
        List<Long> assignedSubjectIds = studentRepository.findSubjectsByStudentId(studentId)
                .stream()
                .map(SubjectEntity::getId)
                .collect(Collectors.toList());

        // Check for duplicates subject should not be assigned to
        if (subjectIds.stream().anyMatch(assignedSubjectIds::contains)) {
            throw new DuplicateResourceException("Subject");
        }
        // If all subject IDs exist, update subjects to student
        studentRepository.updateSubjectsToStudent(studentId, subjectIds);
    }

    public List<StudentDisplayAsSubjects> getAllStudents(){
        return studentMapper.mapStudentEntitiesToStudentDisplayWithSubjects(studentRepository.findAllStudents());
    }

    @Transactional(transactionManager = "schoolManagement", rollbackFor = DuplicateResourceException.class)
    public List<StudentDisplayAsSubjects> assignSubjectSToStudentsByName(SubjectAssignDto subjectAssignDtos) throws DuplicateResourceException, ResourceNotFoundException {
        List<Long> subjectIds;

            subjectIds = subjectRepository.findSubjectIdsByNames(subjectAssignDtos.getSubjects());

            if(subjectIds.isEmpty()) throw new ResourceNotFoundException("Subject");

        // If found duplicate subjectIds
        if (new HashSet<>(subjectIds).size() != subjectIds.size()) {
            throw new DuplicateResourceException("Subjects");
        }

        List<StudentEntity> students = studentRepository.findAllStudents();

        for(StudentEntity student: students) {
            assignSubjectsToStudent(student.getId(), subjectIds);
        }

        return getAllStudents();
    }

}
