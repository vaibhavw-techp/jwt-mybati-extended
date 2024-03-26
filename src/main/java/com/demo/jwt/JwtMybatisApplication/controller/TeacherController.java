package com.demo.jwt.JwtMybatisApplication.controller;


import com.demo.jwt.JwtMybatisApplication.dto.TeacherAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.dto.TeacherSubjectDisplayDto;
import com.demo.jwt.JwtMybatisApplication.model.TeacherEntity;
import com.demo.jwt.JwtMybatisApplication.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    public TeacherEntity addTeacher(@RequestBody TeacherAdditionDto teacherAdditionDto){
        return teacherService.addTeacher(teacherAdditionDto);
    }

    @GetMapping("/{id}")
    public TeacherDisplayDto getTeacherById(@PathVariable long id){
        return teacherService.getTeacherById(id);
    }

    @GetMapping
    public List<TeacherDisplayInfoDto> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}/subject")
    public TeacherSubjectDisplayDto getTeacherWithSubjectsById(@PathVariable Long teacherId) {
       return teacherService.getTeacherWithSubjectsById(teacherId);
    }
}
