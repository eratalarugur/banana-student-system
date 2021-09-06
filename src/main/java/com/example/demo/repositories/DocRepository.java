package com.example.demo.repositories;

import com.example.demo.entities.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Doc repository.
 */
@Repository
public interface DocRepository  extends JpaRepository<Doc,Integer>{

}