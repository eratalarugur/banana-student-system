package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Optional<Student> getStudent(String email) {
        Optional<Student> student = studentRepository.findUserByEmail(email);

        return student;
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student;
    }
}
