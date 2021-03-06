package com.example.demo.repositories;

import com.example.demo.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Teacher repository.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     */
    @Query(value = "SELECT * FROM Teacher t WHERE t.email = :email",nativeQuery = true)
    Optional<Teacher> findUserByEmail(String email);
}
