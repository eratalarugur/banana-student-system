package com.example.demo.controller;

import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/email/{email}")
    public Optional<Student> getStudent(@PathVariable("email") String email) {
        Optional<Student> student = studentService.getStudent(email);
        return student;
    }

    @GetMapping("/id/{id}")
    public Optional<Student> getStudentById(@PathVariable("id")Long id){
        Optional<Student> student = studentService.getStudentById(id);
        return student;
    }
}
