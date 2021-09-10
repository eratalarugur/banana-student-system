package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Assignment;
import com.example.demo.entities.Course;
import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.example.demo.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseService.class})
@ExtendWith(SpringExtension.class)
public class CourseServiceTest {
    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Test
    public void testGetCourseById() {
        Course course = new Course();
        course.setName("Name");
        course.setCourseCode("Course Code");
        course.setDescription("The characteristics of someone or something");
        course.setImage("Image");
        course.setStudents(new ArrayList<Student>());
        course.setTeachers(new ArrayList<Teacher>());
        course.setAssignments(new ArrayList<Assignment>());
        Optional<Course> ofResult = Optional.<Course>of(course);
        when(this.courseRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Course> actualCourseById = this.courseService.getCourseById(123L);
        assertSame(ofResult, actualCourseById);
        assertTrue(actualCourseById.isPresent());
        verify(this.courseRepository).findById((Long) any());
    }
}

