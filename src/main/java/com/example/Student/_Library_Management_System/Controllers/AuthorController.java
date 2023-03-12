package com.example.Student._Library_Management_System.Controllers;

import com.example.Student._Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student._Library_Management_System.Model_Layers.Author;
import com.example.Student._Library_Management_System.ServiceLayers.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class AuthorController {


    @Autowired
    AuthorService authorService;
    // backslash(/) is optional
    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author){

        return authorService.createAuthor(author);

    }
    @GetMapping("/getAuthor")
    public AuthorResponseDto getAuthor(@RequestParam("getAuthor") Integer id){
        return authorService.getAuthor(id);
    }
}
