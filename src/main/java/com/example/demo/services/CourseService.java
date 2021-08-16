package com.example.demo.services;

import com.example.demo.entities.Course;
import com.example.demo.entities.Student;
import com.example.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Optional<Course> getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course;
    }
}
