package com.example.demo.services;

import com.example.demo.requests.LoginRequest;
import com.example.demo.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserService {

    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    /**
     * The Teacher service.
     */
    @Autowired
    TeacherService teacherService;

    /**
     * The Authentication manager.
     */
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * The Password encoder.
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * The Jwt utils.
     */
    @Autowired
    JwtUtils jwtUtils;

    /**
     * Authenticate user response entity.
     *
     * @param loginRequest the login request
     * @return the response entity
     */
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
        if (loginRequest.getIsTeacher() == 1){
            return teacherService.authenticateTeacher(loginRequest);
        }else {
            return studentService.authenticateStudent(loginRequest);
        }
    }





}
