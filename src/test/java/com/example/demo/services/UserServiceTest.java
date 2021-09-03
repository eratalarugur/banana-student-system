package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.requests.LoginRequest;
import com.example.demo.security.jwt.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class, JwtUtils.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @Test
    public void testAuthenticateUser() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.CONTINUE);
        when(this.studentService.authenticateStudent((LoginRequest) any())).thenReturn(responseEntity);
        assertSame(responseEntity,
                this.userService.authenticateUser(new LoginRequest("jane.doe@example.org", "iloveyou", true)));
        verify(this.studentService, atLeast(1)).authenticateStudent((LoginRequest) any());
    }

    @Test
    public void testAuthenticateUser2() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.CONTINUE);
        when(this.teacherService.authenticateTeacher((LoginRequest) any())).thenReturn(responseEntity);
        when(this.studentService.authenticateStudent((LoginRequest) any())).thenReturn(null);
        assertSame(responseEntity,
                this.userService.authenticateUser(new LoginRequest("jane.doe@example.org", "iloveyou", true)));
        verify(this.teacherService).authenticateTeacher((LoginRequest) any());
        verify(this.studentService).authenticateStudent((LoginRequest) any());
    }
}

