package com.example.Student._Library_Management_System.Controllers;

import com.example.Student._Library_Management_System.DTOs.BookRequestDTO;
import com.example.Student._Library_Management_System.Model_Layers.Book;
import com.example.Student._Library_Management_System.ServiceLayers.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

//    @PostMapping("/add")
//    public String addBook(@RequestBody Book book){
//        return bookService.addBook(book);
//    }
@PostMapping("/add")
public String addBook(@RequestBody BookRequestDTO bookRequestDTO){
    return bookService.addBook(bookRequestDTO);
}
}
