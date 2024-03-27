package com.demo.jwt.JwtMybatisApplication.controller;

import com.demo.jwt.JwtMybatisApplication.dto.SubjectAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.SubjectDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.SubjectEntityDisplayDto;
import com.demo.jwt.JwtMybatisApplication.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')")
    public SubjectEntityDisplayDto getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')")
    public List<SubjectDisplayDto> getSubjects(){
        return subjectService.getSubjects();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<SubjectDisplayDto> addSubjects(@RequestBody List<SubjectAdditionDto> subjects){
        return subjectService.addSubjects(subjects);
    }

}
