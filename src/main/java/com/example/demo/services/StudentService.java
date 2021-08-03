package com.example.demo.services;

import com.example.demo.entities.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface StudentService {

    Optional<Student> getStudent(String email);
    Optional<Student> getStudentById(Long id);

}
