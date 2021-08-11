package com.example.demo.controller;

import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/email/{email}")
    public Optional<Teacher> getTeacher(@PathVariable("email") String email) {
        Optional<Teacher> teacher = teacherService.getTeacher(email);
        return teacher;
    }

    @GetMapping("/id/{id}")
    public Optional<Teacher> getStudentById(@PathVariable("id")Long id){
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher;
    }
}
