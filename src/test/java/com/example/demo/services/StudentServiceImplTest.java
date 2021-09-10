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
import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
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

@ContextConfiguration(classes = {StudentServiceImpl.class, JwtUtils.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceImplTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Test
    public void testAuthenticateStudent() throws AuthenticationException {
        when(this.jwtUtils.generateJwtToken((Authentication) any(), anyInt()))
                .thenThrow(new UsernameNotFoundException("Msg"));
        when(this.authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(UsernameNotFoundException.class,
                () -> this.studentServiceImpl.authenticateStudent(new LoginRequest("jane.doe@example.org", "iloveyou", 1)));
        verify(this.jwtUtils).generateJwtToken((Authentication) any(), anyInt());
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    @Test
    public void testAuthenticateStudent2() throws AuthenticationException {
        when(this.jwtUtils.generateJwtToken((Authentication) any(), anyInt())).thenReturn("foo");

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setPassword("iloveyou");
        student.setBirthday("Birthday");
        student.setPicture("Picture");
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setCity("Oxford");
        student.setName("Name");
        student.setSurname("Doe");
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(student, "Credentials");

        when(this.authenticationManager.authenticate((Authentication) any())).thenReturn(testingAuthenticationToken);
        ResponseEntity<?> actualAuthenticateStudentResult = this.studentServiceImpl
                .authenticateStudent(new LoginRequest("jane.doe@example.org", "iloveyou", 1));
        assertTrue(actualAuthenticateStudentResult.hasBody());
        assertTrue(actualAuthenticateStudentResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAuthenticateStudentResult.getStatusCode());
        assertEquals(0L, ((JwtResponse) actualAuthenticateStudentResult.getBody()).getId().longValue());
        assertEquals("jane.doe@example.org", ((JwtResponse) actualAuthenticateStudentResult.getBody()).getEmail());
        assertEquals("Bearer", ((JwtResponse) actualAuthenticateStudentResult.getBody()).getType());
        assertEquals("foo", ((JwtResponse) actualAuthenticateStudentResult.getBody()).getToken());
        verify(this.jwtUtils).generateJwtToken((Authentication) any(), anyInt());
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    @Test
    public void testAuthenticateStudent3() throws AuthenticationException {
        when(this.jwtUtils.generateJwtToken((Authentication) any(), anyInt())).thenReturn("foo");
        Student student = mock(Student.class);
        when(student.getEmail()).thenReturn("foo");
        when(student.getId()).thenReturn(1L);
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(student, "Credentials");

        when(this.authenticationManager.authenticate((Authentication) any())).thenReturn(testingAuthenticationToken);
        ResponseEntity<?> actualAuthenticateStudentResult = this.studentServiceImpl
                .authenticateStudent(new LoginRequest("jane.doe@example.org", "iloveyou", 1));
        assertTrue(actualAuthenticateStudentResult.hasBody());
        assertTrue(actualAuthenticateStudentResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAuthenticateStudentResult.getStatusCode());
        assertEquals(1L, ((JwtResponse) actualAuthenticateStudentResult.getBody()).getId().longValue());
        assertEquals("foo", ((JwtResponse) actualAuthenticateStudentResult.getBody()).getEmail());
        assertEquals("Bearer", ((JwtResponse) actualAuthenticateStudentResult.getBody()).getType());
        assertEquals("foo", ((JwtResponse) actualAuthenticateStudentResult.getBody()).getToken());
        verify(this.jwtUtils).generateJwtToken((Authentication) any(), anyInt());
        verify(this.authenticationManager).authenticate((Authentication) any());
        verify(student).getEmail();
        verify(student).getId();
    }
}

