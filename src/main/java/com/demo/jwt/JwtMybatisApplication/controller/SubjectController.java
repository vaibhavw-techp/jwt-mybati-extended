package com.demo.jwt.JwtMybatisApplication.controller;

import com.demo.jwt.JwtMybatisApplication.dto.SubjectAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.SubjectDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.SubjectEntityDisplayDto;
import com.demo.jwt.JwtMybatisApplication.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/{id}")
    public SubjectEntityDisplayDto getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }
    @GetMapping
    public List<SubjectDisplayDto> getSubjects(){
        return subjectService.getSubjects();
    }

    @PostMapping
    public List<SubjectDisplayDto> addSubjects(@RequestBody List<SubjectAdditionDto> subjects){
        return subjectService.addSubjects(subjects);
    }

}
