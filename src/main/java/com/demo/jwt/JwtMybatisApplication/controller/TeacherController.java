package com.demo.jwt.JwtMybatisApplication.controller;


import com.demo.jwt.JwtMybatisApplication.dto.TeacherAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherSubjectDisplayDto;
import com.demo.jwt.JwtMybatisApplication.model.TeacherEntity;
import com.demo.jwt.JwtMybatisApplication.service.TeacherService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TeacherEntity addTeacher(@RequestBody TeacherAdditionDto teacherAdditionDto){
        return teacherService.addTeacher(teacherAdditionDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_TEACHER') and #id == authentication.token.claims['assc_id'])")
    public TeacherDisplayDto getTeacherById(@PathVariable long id) {
       return teacherService.getTeacherById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<TeacherDisplayInfoDto> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}/subjects")
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_TEACHER') and #id == authentication.token.claims['assc_id'])")
    public TeacherSubjectDisplayDto getTeacherBySubjectsById(@PathVariable Long id) {
        return teacherService.getTeacherWithSubjectsById(id);
    }
}