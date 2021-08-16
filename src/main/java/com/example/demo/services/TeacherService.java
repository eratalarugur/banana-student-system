package com.example.demo.services;

import com.example.demo.entities.Teacher;
import com.example.demo.requests.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TeacherService {

    Optional<Teacher> getTeacher(String email);
    Optional<Teacher> getTeacherById(Long id);
    ResponseEntity<?> authenticateTeacher(LoginRequest loginRequest);
}
