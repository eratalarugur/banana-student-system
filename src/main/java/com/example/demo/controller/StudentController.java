package com.example.demo.controller;

import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Student controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/student")
public class StudentController {

    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    /**
     * Gets student.
     *
     * @param email the email
     * @return the student
     */
    @GetMapping("/email/{email}")
    public Optional<Student> getStudent(@PathVariable("email") String email) {
        Optional<Student> student = studentService.getStudent(email);
        return student;
    }

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    @GetMapping("/id/{id}")
    public Optional<Student> getStudentById(@PathVariable("id")Long id){
        Optional<Student> student = studentService.getStudentById(id);
        return student;
    }
}
