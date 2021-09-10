package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import java.util.Optional;

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
    public void testGetStudent() {
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
        when(this.studentRepository.findUserByEmail((String) any())).thenReturn(ofResult);
        Optional<Student> actualStudent = this.studentServiceImpl.getStudent("jane.doe@example.org");
        assertSame(ofResult, actualStudent);
        assertTrue(actualStudent.isPresent());
        verify(this.studentRepository).findUserByEmail((String) any());
    }

    @Test
    public void testGetStudentById() {
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
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Student> actualStudentById = this.studentServiceImpl.getStudentById(123L);
        assertSame(ofResult, actualStudentById);
        assertTrue(actualStudentById.isPresent());
        verify(this.studentRepository).findById((Long) any());
    }

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

    @Test
    public void testLoadUserByUsername() throws UsernameNotFoundException {
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
        when(this.studentRepository.findUserByEmail((String) any())).thenReturn(ofResult);
        assertSame(student, this.studentServiceImpl.loadUserByUsername("jane.doe@example.org"));
        verify(this.studentRepository).findUserByEmail((String) any());
    }

    @Test
    public void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.studentRepository.findUserByEmail((String) any())).thenReturn(Optional.<Student>empty());
        assertThrows(UsernameNotFoundException.class,
                () -> this.studentServiceImpl.loadUserByUsername("jane.doe@example.org"));
        verify(this.studentRepository).findUserByEmail((String) any());
    }
}

