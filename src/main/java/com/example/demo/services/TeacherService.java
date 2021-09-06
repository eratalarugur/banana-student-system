package com.example.demo.services;

import com.example.demo.entities.Teacher;
import com.example.demo.requests.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * The interface Teacher service.
 */
public interface TeacherService {

    /**
     * Gets teacher.
     *
     * @param email the email
     * @return the teacher
     */
    Optional<Teacher> getTeacher(String email);

    /**
     * Gets teacher by id.
     *
     * @param id the id
     * @return the teacher by id
     */
    Optional<Teacher> getTeacherById(Long id);

    /**
     * Authenticate teacher response entity.
     *
     * @param loginRequest the login request
     * @return the response entity
     */
    ResponseEntity<?> authenticateTeacher(LoginRequest loginRequest);
}
