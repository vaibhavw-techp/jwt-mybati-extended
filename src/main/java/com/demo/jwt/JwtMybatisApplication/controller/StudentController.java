package com.demo.jwt.JwtMybatisApplication.controller;



import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import com.demo.jwt.JwtMybatisApplication.service.StudentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/{id}")

    @RolesAllowed({"ROLE_ADMIN", "ROLE_STUDENT", "ROLE_TEACHER"})
    public StudentDisplayByIdDto getStudentById(@PathVariable Long id, Principal principal) {
       return studentService.getStudentById(id, principal);
    }

    @PostMapping("/{studentId}/subjects")
    @RolesAllowed("ROLE_ADMIN")
    public void assignSubjectsToStudent(@PathVariable Long studentId, @RequestBody List<SubjectEntity> subjects) {
        studentService.assignSubjectsToStudent(studentId, subjects);
    }

    // Add student
    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public StudentDisplayByIdDto addStudent(@RequestBody StudentAddDto student){
        return studentService.addStudent(student);
    }

    @GetMapping("/{studentId}/subjects")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_STUDENT"})

    public StudentDisplayAsSubjects getStudentWithSubjects(@PathVariable Long studentId, Principal principal) {
        return studentService.getStudentWithSubjects(studentId, principal);
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
}