package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Course;
import com.example.demo.entities.Teacher;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.requests.LoginRequest;
import com.example.demo.responses.JwtResponse;
import com.example.demo.security.jwt.JwtUtils;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TeacherServiceImpl.class, JwtUtils.class})
@ExtendWith(SpringExtension.class)
public class TeacherServiceImplTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @Test
    public void testAuthenticateTeacher() throws AuthenticationException {
        when(this.jwtUtils.generateJwtToken((Authentication) any(), anyInt()))
                .thenThrow(new UsernameNotFoundException("Msg"));
        when(this.authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(UsernameNotFoundException.class,
                () -> this.teacherServiceImpl.authenticateTeacher(new LoginRequest("jane.doe@example.org", "iloveyou", 1)));
        verify(this.jwtUtils).generateJwtToken((Authentication) any(), anyInt());
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    @Test
    public void testAuthenticateTeacher2() throws AuthenticationException {
        when(this.jwtUtils.generateJwtToken((Authentication) any(), anyInt())).thenReturn("foo");

        Teacher teacher = new Teacher();
        teacher.setEmail("jane.doe@example.org");
        teacher.setPassword("iloveyou");
        teacher.setAssignedCourses(new ArrayList<Course>());
        teacher.setBirthday("Birthday");
        teacher.setPicture("Picture");
        teacher.setCity("Oxford");
        teacher.setName("Name");
        teacher.setSurname("Doe");
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(teacher, "Credentials");

        when(this.authenticationManager.authenticate((Authentication) any())).thenReturn(testingAuthenticationToken);
        ResponseEntity<?> actualAuthenticateTeacherResult = this.teacherServiceImpl
                .authenticateTeacher(new LoginRequest("jane.doe@example.org", "iloveyou", 1));
        assertTrue(actualAuthenticateTeacherResult.hasBody());
        assertTrue(actualAuthenticateTeacherResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAuthenticateTeacherResult.getStatusCode());
        assertEquals(0L, ((JwtResponse) actualAuthenticateTeacherResult.getBody()).getId().longValue());
        assertEquals("jane.doe@example.org", ((JwtResponse) actualAuthenticateTeacherResult.getBody()).getEmail());
        assertEquals("Bearer", ((JwtResponse) actualAuthenticateTeacherResult.getBody()).getType());
        assertEquals("foo", ((JwtResponse) actualAuthenticateTeacherResult.getBody()).getToken());
        verify(this.jwtUtils).generateJwtToken((Authentication) any(), anyInt());
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    @Test
    public void testAuthenticateTeacher3() throws AuthenticationException {
        when(this.jwtUtils.generateJwtToken((Authentication) any(), anyInt())).thenReturn("foo");
        Teacher teacher = mock(Teacher.class);
        when(teacher.getEmail()).thenReturn("foo");
        when(teacher.getId()).thenReturn(1L);
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(teacher, "Credentials");

        when(this.authenticationManager.authenticate((Authentication) any())).thenReturn(testingAuthenticationToken);
        ResponseEntity<?> actualAuthenticateTeacherResult = this.teacherServiceImpl
                .authenticateTeacher(new LoginRequest("jane.doe@example.org", "iloveyou", 1));
        assertTrue(actualAuthenticateTeacherResult.hasBody());
        assertTrue(actualAuthenticateTeacherResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAuthenticateTeacherResult.getStatusCode());
        assertEquals(1L, ((JwtResponse) actualAuthenticateTeacherResult.getBody()).getId().longValue());
        assertEquals("foo", ((JwtResponse) actualAuthenticateTeacherResult.getBody()).getEmail());
        assertEquals("Bearer", ((JwtResponse) actualAuthenticateTeacherResult.getBody()).getType());
        assertEquals("foo", ((JwtResponse) actualAuthenticateTeacherResult.getBody()).getToken());
        verify(this.jwtUtils).generateJwtToken((Authentication) any(), anyInt());
        verify(this.authenticationManager).authenticate((Authentication) any());
        verify(teacher).getEmail();
        verify(teacher).getId();
    }
}

