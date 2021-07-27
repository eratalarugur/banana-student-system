package com.example.demo.repositories;

import com.example.demo.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository <Teacher, Long> {
}
