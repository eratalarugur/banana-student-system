package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Assignment;
import com.example.demo.entities.Course;
import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.example.demo.services.CourseService;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CourseController.class})
@ExtendWith(SpringExtension.class)
public class CourseControllerTest {
    @Autowired
    private CourseController courseController;

    @MockBean
    private CourseService courseService;

    @Test
    public void testGetStudentById() throws Exception {
        Course course = new Course();
        course.setName("Name");
        course.setCourseCode("Course Code");
        course.setStudents(new ArrayList<Student>());
        course.setTeachers(new ArrayList<Teacher>());
        course.setAssignments(new ArrayList<Assignment>());
        Optional<Course> ofResult = Optional.<Course>of(course);
        when(this.courseService.getCourseById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/course/id/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":0,\"name\":\"Name\",\"courseCode\":\"Course Code\",\"assignments\":[]}"));
    }
}

