package com.example.Student._Library_Management_System.Repositories;

import com.example.Student._Library_Management_System.Model_Layers.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepositories extends JpaRepository<Card,Integer> {
}
