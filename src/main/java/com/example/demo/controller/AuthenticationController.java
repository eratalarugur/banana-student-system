package com.example.demo.controller;

import com.example.demo.requests.LoginRequest;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/signin")
public class AuthenticationController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @PostMapping("/student")
    public ResponseEntity<?> authenticateStudent(@Valid @RequestBody LoginRequest loginRequest){
        System.out.println("====>>>> Email : " + loginRequest.getEmail());
        System.out.println("====>>>> Password : " + loginRequest.getPassword());
        return studentService.authenticateStudent(loginRequest);
    }

    @PostMapping("/teacher")
    public ResponseEntity<?> authenticateTeacher(@Valid @RequestBody LoginRequest loginRequest){
        return teacherService.authenticateTeacher(loginRequest);
    }
}
