package com.example.Student._Library_Management_System.Repositories;

import com.example.Student._Library_Management_System.Model_Layers.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
