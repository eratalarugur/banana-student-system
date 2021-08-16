package com.example.demo.controller;

import com.example.demo.entities.Course;
import com.example.demo.entities.Teacher;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/id/{id}")
    public Optional<Course> getStudentById(@PathVariable("id")Long id){
        Optional<Course> course = courseService.getCourseById(id);
        return course;
    }
}
