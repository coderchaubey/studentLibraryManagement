package com.example.Student._Library_Management_System.ServiceLayers;

import com.example.Student._Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student._Library_Management_System.DTOs.BookResponseDto;
import com.example.Student._Library_Management_System.Model_Layers.Author;
import com.example.Student._Library_Management_System.Model_Layers.Book;
import com.example.Student._Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public String createAuthor(Author author){

        authorRepository.save(author);
        return "Author added successfully";
    }
    public AuthorResponseDto getAuthor(Integer authorId){

        Author author=authorRepository.findById(authorId).get();

        AuthorResponseDto authorResponseDto=new AuthorResponseDto();

        //Set its Attributes.

        //Converting
        //List<Book> --> List<BookResponseDto>
        List<Book> bookList=author.getBooksWritten();

        List<BookResponseDto> booksWrittenDto=new ArrayList<>();

        for(Book b: bookList){
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setName(b.getName());

            booksWrittenDto.add(bookResponseDto);
        }

        //Set attributes for converting to authorResponse Dto
        authorResponseDto.setBooksWritten(booksWrittenDto);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());

        return authorResponseDto;
    }
}
