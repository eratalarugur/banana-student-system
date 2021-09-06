package com.example.demo.repositories;

import com.example.demo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Student repository.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     */
    @Query(value = "SELECT * FROM Student s WHERE s.email = :email",nativeQuery = true)
    Optional<Student> findUserByEmail(String email);
}
