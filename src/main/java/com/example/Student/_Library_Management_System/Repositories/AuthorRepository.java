package com.example.Student._Library_Management_System.Repositories;

import com.example.Student._Library_Management_System.Model_Layers.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
