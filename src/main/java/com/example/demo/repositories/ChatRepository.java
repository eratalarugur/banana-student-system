package com.example.demo.repositories;

import com.example.demo.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository  extends JpaRepository<Chat, Long> {
    @Query(value = "SELECT * FROM Chat c WHERE c.course_id = :course_id",nativeQuery = true)
    List<Chat> findAllById(Long course_id);
}
