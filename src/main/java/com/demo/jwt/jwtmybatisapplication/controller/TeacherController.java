package com.demo.jwt.jwtmybatisapplication.controller;


import com.demo.jwt.jwtmybatisapplication.dto.TeacherAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.TeacherDisplayDto;
import com.demo.jwt.jwtmybatisapplication.dto.TeacherDisplayInfoDto;
import com.demo.jwt.jwtmybatisapplication.dto.TeacherSubjectDisplayDto;
import com.demo.jwt.jwtmybatisapplication.model.TeacherEntity;
import com.demo.jwt.jwtmybatisapplication.service.TeacherService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    @RolesAllowed("ADMIN")
    public TeacherEntity addTeacher(@RequestBody TeacherAdditionDto teacherAdditionDto) {
        return teacherService.addTeacher(teacherAdditionDto);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "TEACHER"})
    public TeacherDisplayDto getTeacherById(@PathVariable long id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping
    @RolesAllowed({"ADMIN", "TEACHER"})
    public List<TeacherDisplayInfoDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}/subject")
    @RolesAllowed({"ADMIN", "TEACHER"})
    public TeacherSubjectDisplayDto getTeacherBySubjectsById(@PathVariable Long teacherId) {
        return teacherService.getTeacherWithSubjectsById(teacherId);
    }
}
