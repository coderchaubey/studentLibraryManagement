package com.example.Student._Library_Management_System.Repositories;

import com.example.Student._Library_Management_System.Model_Layers.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepositories extends JpaRepository<Student,Integer> {

    //Abstract way of using a partial defined Query
    Student findByEmail(String email);

    //select * from student where country=India; //Return of Entities
    List<Student> findByCountry(String country);
}
