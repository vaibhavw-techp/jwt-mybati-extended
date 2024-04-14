package com.demo.jwt.JwtMybatisApplication.controller;


//import com.demo.jwt.JwtMybatisApplication.config.jwt.JwtTokenExtractor;
//import com.demo.jwt.JwtMybatisApplication.config.jwt.RateLimiterService;
import com.demo.jwt.JwtMybatisApplication.config.jwt.BypassRateLimit;
import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.service.StudentService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private final Bucket bucket;

    public StudentController(Bucket bucket) {
        this.bucket = bucket;
    }

    public StudentController() {
        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER') or (hasRole('ROLE_STUDENT') and #studentId == authentication.token.claims['assc_id'])")
    public StudentDisplayByIdDto getStudentById(@PathVariable Long studentId) {
        // Check rate limit before processing the request
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
    public StudentDisplaySubjectsDto getStudentWithSubjects(@PathVariable Long studentId) {
        return studentService.getStudentWithSubjects(studentId);
    }

    // Filter + ALL
    @GetMapping
    @BypassRateLimit
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public List<StudentDisplayDto> getAllStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String email) {

        // Check rate limit before processing the request
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            // Request allowed, proceed with processing
            List<StudentDisplayDto> studentsDisplayDtos = studentService.getAllStudentsWithFilters(name, age, email);
            return studentsDisplayDtos;
        } else {
            // Request denied due to rate limit exceeded
            throw new RuntimeException("API request limit has been exhausted.");
        }

//        List<StudentDisplayDto> studentsDisplayDtos = studentService.getAllStudentsWithFilters(name, age, email);
//        return studentsDisplayDtos;
    }
}