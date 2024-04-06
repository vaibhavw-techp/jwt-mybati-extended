package com.demo.jwt.JwtMybatisApplication.controller;

import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.exceptions.DuplicateResourceException;
import com.demo.jwt.JwtMybatisApplication.exceptions.ResourceNotFoundException;
import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import com.demo.jwt.JwtMybatisApplication.service.StudentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STUDENT"})
    public StudentDisplayByIdDto getStudentById(@PathVariable Long id) throws ResourceNotFoundException {
        return studentService.getStudentById(id);
    }

//    @PostMapping("/{studentId}/subjects")
//    @RolesAllowed("ROLE_ADMIN")
//    public void assignSubjectsToStudent(@PathVariable Long studentId, @RequestBody List<Long> subjects) {
//        studentService.assignSubjectsToStudent(studentId, subjects);
//    }

    // Add student
    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public StudentDisplayByIdDto addStudent(@RequestBody StudentAddDto student){
        return studentService.addStudent(student);
    }

    @GetMapping("/{studentId}/subjects")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STUDENT"})
    public StudentDisplayAsSubjects getStudentWithSubjects(@PathVariable Long studentId) {
        return studentService.getStudentWithSubjects(studentId);
    }

    // Filter + ALL
    @GetMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STUDENT"})
    public List<StudentDisplayDto> getAllStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String email) {

        List<StudentDisplayDto> studentsDisplayDtos = studentService.getAllStudentsWithFilters(name, age, email);
        return studentsDisplayDtos;
    }

    @PostMapping("/assign")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STUDENT"})
    public List<StudentDisplayAsSubjects> assignSubjectsToStudent(@RequestBody SubjectAssignDto subjectAssignDto) throws ResourceNotFoundException, DuplicateResourceException {
        return studentService.assignSubjectSToStudentsByName(subjectAssignDto);
    }

}