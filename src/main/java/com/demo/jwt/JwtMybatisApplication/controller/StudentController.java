package com.demo.jwt.JwtMybatisApplication.controller;

import com.demo.jwt.JwtMybatisApplication.dto.StudentAddDto;
import com.demo.jwt.JwtMybatisApplication.dto.StudentDisplayAsSubjects;
import com.demo.jwt.JwtMybatisApplication.dto.StudentDisplayByIdDto;
import com.demo.jwt.JwtMybatisApplication.dto.StudentsDisplayDto;
import com.demo.jwt.JwtMybatisApplication.model.SubjectEntity;
import com.demo.jwt.JwtMybatisApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public StudentDisplayByIdDto getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/{studentId}/subjects")
    public void assignSubjectsToStudent(@PathVariable Long studentId, @RequestBody List<SubjectEntity> subjects) {
        studentService.assignSubjectsToStudent(studentId, subjects);
    }

    // Add student
    @PostMapping
    public StudentDisplayByIdDto addStudent(@RequestBody StudentAddDto student){
        return studentService.addStudent(student);
    }

    @GetMapping("/{studentId}/subjects")
    public StudentDisplayAsSubjects getStudentWithSubjects(@PathVariable Long studentId) {
        return studentService.getStudentWithSubjects(studentId);
    }

    // Filter + ALL
    @GetMapping
    public List<StudentsDisplayDto> getAllStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String email) {

        List<StudentsDisplayDto> studentsDisplayDtos = studentService.getAllStudentsWithFilters(name, age, email);
        return studentsDisplayDtos;
    }
}
