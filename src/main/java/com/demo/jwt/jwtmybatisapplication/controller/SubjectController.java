package com.demo.jwt.jwtmybatisapplication.controller;

import com.demo.jwt.jwtmybatisapplication.dto.SubjectAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.SubjectDisplayDto;
import com.demo.jwt.jwtmybatisapplication.dto.SubjectEntityDisplayDto;
import com.demo.jwt.jwtmybatisapplication.service.SubjectService;
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
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "STUDENT", "TEACHER"})
    public SubjectEntityDisplayDto getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }
    @GetMapping
    @RolesAllowed({"ADMIN", "STUDENT", "TEACHER"})
    public List<SubjectDisplayDto> getSubjects() {
        return subjectService.getSubjects();
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public List<SubjectDisplayDto> addSubjects(@RequestBody List<SubjectAdditionDto> subjects) {
        return subjectService.addSubjects(subjects);
    }

}
