package com.demo.jwt.JwtMybatisApplication.controller;


import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER') or (hasRole('ROLE_STUDENT') and #studentId == authentication.token.claims['assc_id'])")
    public StudentDisplayByIdDto getStudentById(@PathVariable Long studentId) {
       return studentService.getStudentById(studentId);
    }

    // Add student
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StudentDisplayByIdDto addStudent(@RequestBody StudentAddDto student){
        return studentService.addStudent(student);
    }

    @GetMapping("/{studentId}/subjects")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER') or (hasRole('ROLE_STUDENT') and #studentId == authentication.token.claims['assc_id'])")
    public StudentDisplayAsSubjects getStudentWithSubjects(@PathVariable Long studentId) {
        return studentService.getStudentWithSubjects(studentId);
    }

    // Filter + ALL
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<StudentDisplayDto> getAllStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String email) {

        List<StudentDisplayDto> studentsDisplayDtos = studentService.getAllStudentsWithFilters(name, age, email);
        return studentsDisplayDtos;
    }
}