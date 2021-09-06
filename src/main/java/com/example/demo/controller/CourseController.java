package com.example.demo.controller;

import com.example.demo.entities.Course;
import com.example.demo.entities.Teacher;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The type Course controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/course")
public class CourseController {

    /**
     * The Course service.
     */
    @Autowired
    CourseService courseService;

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    @GetMapping("/id/{id}")
    public Optional<Course> getStudentById(@PathVariable("id")Long id){
        Optional<Course> course = courseService.getCourseById(id);
        return course;
    }
}
