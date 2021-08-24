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
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest){
        System.out.println("====>>>> Email : " + loginRequest.getEmail());
        System.out.println("====>>>> Password : " + loginRequest.getPassword());
        System.out.println("====>>>> isTeacher : " + loginRequest.isTeacher());
        if( loginRequest.isTeacher()){
            return teacherService.authenticateTeacher(loginRequest);
        } else {
            return studentService.authenticateStudent(loginRequest);
        }
    }

    @GetMapping("/detail")
    public Object getUser(@RequestBody UserDetailRequest userDetailRequest) {
        if (userDetailRequest.isTeacher()){
            return teacherService.getTeacher(userDetailRequest.getEmail());
        } else {
            return studentService.getStudent((userDetailRequest.getEmail()));
        }


    }

}
