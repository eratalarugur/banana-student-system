package com.example.demo.services;

import com.example.demo.entities.Teacher;
import com.example.demo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Optional<Teacher> getTeacher(String email) {
        Optional<Teacher> teacher = teacherRepository.findUserByEmail(email);
        return teacher;
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher;
    }
}
