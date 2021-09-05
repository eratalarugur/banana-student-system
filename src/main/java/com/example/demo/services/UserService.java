package com.example.demo.services;

import com.example.demo.requests.LoginRequest;
import com.example.demo.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
        System.out.println("====>>>> authenticateUser Email : " + loginRequest.getEmail());
        System.out.println("====>>>> authenticateUser Password : " + loginRequest.getPassword());
        System.out.println("====>>>> authenticateUser isTeacher : " + loginRequest.getIsTeacher());
        if (loginRequest.getIsTeacher() == 1){
            return teacherService.authenticateTeacher(loginRequest);
        }else {
            return studentService.authenticateStudent(loginRequest);
        }
    }





}
