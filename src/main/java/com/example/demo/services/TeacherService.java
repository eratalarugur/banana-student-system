package com.example.demo.services;

import com.example.demo.entities.Teacher;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface TeacherService {

    Optional<Teacher> getTeacher(String email);
    Optional<Teacher> getTeacherById(Long id);

}
