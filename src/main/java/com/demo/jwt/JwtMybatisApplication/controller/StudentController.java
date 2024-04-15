package com.demo.jwt.JwtMybatisApplication.controller;


//import com.demo.jwt.JwtMybatisApplication.config.jwt.JwtTokenExtractor;
//import com.demo.jwt.JwtMybatisApplication.config.jwt.RateLimiterService;
import com.demo.jwt.JwtMybatisApplication.exceptions.ManyRequestsException;
import com.demo.jwt.JwtMybatisApplication.ratelimit.PricingPlan;
import com.demo.jwt.JwtMybatisApplication.ratelimit.RateLimitingService;
import com.demo.jwt.JwtMybatisApplication.dto.*;
import com.demo.jwt.JwtMybatisApplication.service.StudentService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Duration;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private final Bucket bucket;

    @Autowired
    private RateLimitingService rateLimitingService;

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
    public StudentDisplayByIdDto getStudentById(@PathVariable Long studentId, Principal principal) {
        return studentService.getStudentById(studentId, principal);
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public List<StudentDisplayDto> getAllStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String email, Principal principal) {


        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) principal;
        Jwt jwt = jwtAuthenticationToken.getToken();
        String username = principal.getName();
        String plan = (String) jwt.getClaims().get("plan");

        try {
            if (rateLimitingService.tryConsume(username, plan)) {
                return studentService.getAllStudentsWithFilters(name,age,email);
            } else {
                throw new ManyRequestsException();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while processing rate limiting: " + e.getMessage());
        }

    }


}