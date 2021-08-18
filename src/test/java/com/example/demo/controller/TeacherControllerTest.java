package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Course;
import com.example.demo.entities.Teacher;
import com.example.demo.services.TeacherService;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TeacherController.class})
@ExtendWith(SpringExtension.class)
public class TeacherControllerTest {
    @Autowired
    private TeacherController teacherController;

    @MockBean
    private TeacherService teacherService;

    @Test
    public void testGetStudentById() throws Exception {
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
        when(this.teacherService.getTeacherById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teacher/id/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.teacherController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"name\":\"Name\",\"surname\":\"Doe\",\"birthday\":\"Birthday\",\"email\":\"jane.doe@example.org\",\"picture\""
                                        + ":\"Picture\",\"city\":\"Oxford\",\"password\":\"iloveyou\",\"assignedCourses\":[],\"enabled\":true,\"accountNonLocked"
                                        + "\":true,\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"username\":\"jane.doe@example.org\"}"));
    }

    @Test
    public void testGetTeacher() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teacher/email/{email}",
                "jane.doe@example.org");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.teacherController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"name\":\"Name\",\"surname\":\"Doe\",\"birthday\":\"Birthday\",\"email\":\"jane.doe@example.org\",\"picture\""
                                        + ":\"Picture\",\"city\":\"Oxford\",\"password\":\"iloveyou\",\"assignedCourses\":[],\"enabled\":true,\"accountNonLocked"
                                        + "\":true,\"credentialsNonExpired\":true,\"accountNonExpired\":true,\"username\":\"jane.doe@example.org\"}"));
    }
}

