package com.example.demo.services;

import com.example.demo.entities.Student;


import java.util.Optional;

public interface StudentService {

    Optional<Student> getStudent(String email);
    Optional<Student> getStudentById(Long id);

}
