package com.demo.jwt.JwtMybatisApplication.controller;


import com.demo.jwt.JwtMybatisApplication.dto.TeacherAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherSubjectDisplayDto;
import com.demo.jwt.JwtMybatisApplication.model.TeacherEntity;
import com.demo.jwt.JwtMybatisApplication.service.TeacherService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SecurityService securityService;

    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public TeacherEntity addTeacher(@RequestBody TeacherAdditionDto teacherAdditionDto){
        return teacherService.addTeacher(teacherAdditionDto);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN, ROLE_TEACHER"})
    public TeacherDisplayDto getTeacherById(@PathVariable long id, Principal principal) {
       return teacherService.getTeacherById(id, principal);
    }

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_TEACHER"})
    public List<TeacherDisplayInfoDto> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}/subject")
    @RolesAllowed({"ROLE_ADMIN","ROLE_TEACHER"})
    public TeacherSubjectDisplayDto getTeacherBySubjectsById(@PathVariable Long teacherId, Principal principal) {
        return teacherService.getTeacherWithSubjectsById(teacherId, principal);
    }
}