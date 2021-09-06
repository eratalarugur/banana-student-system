package com.example.demo.repositories;

import com.example.demo.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Chat repository.
 */
@Repository
public interface ChatRepository  extends JpaRepository<Chat, Long> {
    /**
     * Find all by id list.
     *
     * @param course_id the course id
     * @return the list
     */
    @Query(value = "SELECT * FROM Chat c WHERE c.course_id = :course_id",nativeQuery = true)
    List<Chat> findAllById(Long course_id);
}
