package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Course;
import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.example.demo.requests.LoginRequest;
import com.example.demo.requests.UserDetailRequest;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthenticationController.class})
@ExtendWith(SpringExtension.class)
public class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController authenticationController;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    @Test
    public void testGetUser() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setEmail("jane.doe@example.org");
        teacher.setPassword("iloveyou");
        teacher.setAssignedCourses(new ArrayList<Course>());
        teacher.setBirthday("Birthday");
        teacher.setPicture("Picture");
        teacher.setCity("Oxford");
        teacher.setName("Name");
        teacher.setSurname("Doe");
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherService.getTeacher((String) any())).thenReturn(ofResult);

        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setEmail("jane.doe@example.org");
        userDetailRequest.setTeacher(true);
        String content = (new ObjectMapper()).writeValueAsString(userDetailRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"name\":\"Name\",\"surname\":\"Doe\",\"birthday\":\"Birthday\",\"email\":\"jane.doe@example.org\",\"picture\""
                                        + ":\"Picture\",\"city\":\"Oxford\",\"password\":\"iloveyou\",\"assignedCourses\":[],\"enabled\":true,\"username\":\"jane"
                                        + ".doe@example.org\",\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true,"
                                        + "\"authorities\":[{\"authority\":\"TEACHER\"}]}"));
    }

    @Test
    public void testGetUser2() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setEmail("jane.doe@example.org");
        teacher.setPassword("iloveyou");
        teacher.setAssignedCourses(new ArrayList<Course>());
        teacher.setBirthday("Birthday");
        teacher.setPicture("Picture");
        teacher.setCity("Oxford");
        teacher.setName("Name");
        teacher.setSurname("Doe");
        Optional<Teacher> ofResult = Optional.<Teacher>of(teacher);
        when(this.teacherService.getTeacher((String) any())).thenReturn(ofResult);

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setPassword("iloveyou");
        student.setBirthday("Birthday");
        student.setPicture("Picture");
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setCity("Oxford");
        student.setName("Name");
        student.setSurname("Doe");
        Optional<Student> ofResult1 = Optional.<Student>of(student);
        when(this.studentService.getStudent((String) any())).thenReturn(ofResult1);

        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setEmail("jane.doe@example.org");
        userDetailRequest.setTeacher(false);
        String content = (new ObjectMapper()).writeValueAsString(userDetailRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"name\":\"Name\",\"surname\":\"Doe\",\"birthday\":\"Birthday\",\"email\":\"jane.doe@example.org\",\"picture\""
                                        + ":\"Picture\",\"city\":\"Oxford\",\"password\":\"iloveyou\",\"registeredCourses\":[],\"enabled\":true,\"username\":"
                                        + "\"jane.doe@example.org\",\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true"
                                        + ",\"authorities\":[{\"authority\":\"STUDENT\"}]}"));
    }
}

