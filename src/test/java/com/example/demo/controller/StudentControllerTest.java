package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Course;
import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;

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

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetStudent() throws Exception {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setPassword("iloveyou");
        student.setBirthday("Birthday");
        student.setPicture("Picture");
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setCity("Oxford");
        student.setName("Name");
        student.setSurname("Doe");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentService.getStudent((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/student/email/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"name\":\"Name\",\"surname\":\"Doe\",\"birthday\":\"Birthday\",\"email\":\"jane.doe@example.org\",\"picture\""
                                        + ":\"Picture\",\"city\":\"Oxford\",\"password\":\"iloveyou\",\"registeredCourses\":[],\"enabled\":true,\"username\":"
                                        + "\"jane.doe@example.org\",\"authorities\":[{\"authority\":\"STUDENT\"}],\"credentialsNonExpired\":true,"
                                        + "\"accountNonLocked\":true,\"accountNonExpired\":true}"));
    }

    @Test
    public void testGetStudentById() throws Exception {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setPassword("iloveyou");
        student.setBirthday("Birthday");
        student.setPicture("Picture");
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setCity("Oxford");
        student.setName("Name");
        student.setSurname("Doe");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentService.getStudentById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/student/id/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"name\":\"Name\",\"surname\":\"Doe\",\"birthday\":\"Birthday\",\"email\":\"jane.doe@example.org\",\"picture\""
                                        + ":\"Picture\",\"city\":\"Oxford\",\"password\":\"iloveyou\",\"registeredCourses\":[],\"enabled\":true,\"username\":"
                                        + "\"jane.doe@example.org\",\"authorities\":[{\"authority\":\"STUDENT\"}],\"credentialsNonExpired\":true,"
                                        + "\"accountNonLocked\":true,\"accountNonExpired\":true}"));
    }
}

