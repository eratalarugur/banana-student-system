package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.requests.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * The interface Student service.
 */
public interface StudentService {

    /**
     * Gets student.
     *
     * @param email the email
     * @return the student
     */
    Optional<Student> getStudent(String email);

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    Optional<Student> getStudentById(Long id);

    /**
     * Authenticate student response entity.
     *
     * @param loginRequest the login request
     * @return the response entity
     */
    ResponseEntity<?> authenticateStudent(LoginRequest loginRequest);
}
