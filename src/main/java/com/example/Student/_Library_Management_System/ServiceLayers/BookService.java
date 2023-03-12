package com.example.Student._Library_Management_System.ServiceLayers;

import com.example.Student._Library_Management_System.DTOs.BookRequestDTO;
import com.example.Student._Library_Management_System.Model_Layers.Author;
import com.example.Student._Library_Management_System.Model_Layers.Book;
import com.example.Student._Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository; //for getting Author Entity we need the bean

//    public String addBook(Book book){
//
//
//        //I want to get the Author Entity
//        //but how?
//        int authorId=book.getAuthor().getId();
//
//        //Now I will be fetching the authorEntity
//        Author author=authorRepository.findById(authorId).get(); //.get() will throw an exception if the author not found
//        //homework catch exception
//
////        Basic attributes are already set from postman
////        Setting the foreign key attr in the child class:
//        book.setAuthor(author);
//
//        // We need to update the listOfBooks written in the parent class
//        List<Book> currentBooksWritten = author.getBooksWritten();
////        adding
//        currentBooksWritten.add(book);
//
//
////        Now the book is to be saved, but also author is also to be saved
////        Why do we need to update the author??
////        becz the author entity has been updated we need to resave it
//
//        authorRepository.save(author); //Data was modified
//        //.save function work both for saving and updating
//
////        BookRepo.save is not required bcz it will be auto called by cascading
////        effect
//
//        return "Book added successfully";
//    }

    /********Conversion process below****/
public String addBook(BookRequestDTO bookRequestDTO){


    //I want to get the Author Entity
    //but how?
    int authorId= bookRequestDTO.getAuthorId();

    //Now I will be fetching the authorEntity
    Author author=authorRepository.findById(authorId).get(); //.get() will throw an exception if the author not found


    //Convertor
    //We have created this entity so that we can save it to the DB.
    Book book=new Book();


    //Basic attributes are being saved from the Dto to the Entity Layer
    book.setGenre(bookRequestDTO.getGenre());
    book.setIssued(false);
    book.setName(bookRequestDTO.getName());
    book.setPages(bookRequestDTO.getPages());



//        Setting the foreign key attr in the child class:
    book.setAuthor(author);

    // We need to update the listOfBooks written in the parent class
    List<Book> currentBooksWritten = author.getBooksWritten();
//        adding
    currentBooksWritten.add(book);


//        Now the book is to be saved, but also author is also to be saved
//        Why do we need to update the author??
//        becz the author entity has been updated we need to resave it

    authorRepository.save(author); //Data was modified
    //.save function work both for saving and updating

//        BookRepo.save is not required bcz it will be auto called by cascading
//        effect

    return "Book added successfully";
}
}
