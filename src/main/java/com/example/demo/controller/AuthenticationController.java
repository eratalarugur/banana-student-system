package com.example.demo.controller;

import com.example.demo.entities.Student;
import com.example.demo.requests.LoginRequest;
import com.example.demo.requests.UserDetailRequest;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest){
        System.out.println("====>>>> authenticate login Email : " + loginRequest.getEmail());
        System.out.println("====>>>> authenticate login Password : " + loginRequest.getPassword());
        System.out.println("====>>>> authenticate login isTeacher : " + loginRequest.getIsTeacher());
        if( loginRequest.getIsTeacher() == 1){
            return teacherService.authenticateTeacher(loginRequest);
        } else {
            return studentService.authenticateStudent(loginRequest);
        }
    }

    @GetMapping("/detail")
    public Object getUser(@RequestBody UserDetailRequest userDetailRequest) {
        if (userDetailRequest.getIsTeacher() == 1){
            System.out.println("====>>>> getUser detail Teacher Email : " + userDetailRequest.getEmail());
            System.out.println("====>>>> getUser detail Teacher isTeacher : " + userDetailRequest.getIsTeacher());
            return teacherService.getTeacher(userDetailRequest.getEmail());
        } else {
            System.out.println("====>>>> getUser detail Student Email : " + userDetailRequest.getEmail());
            System.out.println("====>>>> getUser detail Student isTeacher : " + userDetailRequest.getIsTeacher());
            return studentService.getStudent((userDetailRequest.getEmail()));
        }
    }
}
