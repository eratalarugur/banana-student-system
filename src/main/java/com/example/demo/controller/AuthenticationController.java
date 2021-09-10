package com.example.demo.controller;

import com.example.demo.requests.LoginRequest;
import com.example.demo.requests.UserDetailRequest;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * The type Authentication controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class AuthenticationController {

    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    /**
     * The Teacher service.
     */
    @Autowired
    TeacherService teacherService;


    /**
     * Authenticate response entity.
     *
     * @param loginRequest the login request
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest){
        if( loginRequest.getIsTeacher() == 1){
            return teacherService.authenticateTeacher(loginRequest);
        } else {
            return studentService.authenticateStudent(loginRequest);
        }
    }


    /**
     * Gets user.
     *
     * @param userDetailRequest the user detail request
     * @return the user
     */
    @PostMapping("/detail")
    public Object getUser(@RequestBody UserDetailRequest userDetailRequest) {
        if (userDetailRequest.getIsTeacher() == 1){
            return teacherService.getTeacher(userDetailRequest.getEmail());
        } else {
            return studentService.getStudent((userDetailRequest.getEmail()));
        }
    }
}
