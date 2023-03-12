package com.example.Student._Library_Management_System.ServiceLayers;


import com.example.Student._Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student._Library_Management_System.Enums.CardStatus;
import com.example.Student._Library_Management_System.Enums.TransactionStatus;
import com.example.Student._Library_Management_System.Model_Layers.Book;
import com.example.Student._Library_Management_System.Model_Layers.Card;
import com.example.Student._Library_Management_System.Model_Layers.Transactions;
import com.example.Student._Library_Management_System.Repositories.BookRepository;
import com.example.Student._Library_Management_System.Repositories.CardRepositories;
import com.example.Student._Library_Management_System.Repositories.TransactionRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {


    @Autowired
    TransactionRepositories transactionRepositories;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepositories cardRepositories;

    public String issueBook(IssueBookRequestDto issueBookRequestDto)throws Exception{


        int bookId= issueBookRequestDto.getBookId();
        int cardId= issueBookRequestDto.getCardId();

        //Get the Book Entity and Card Entity. Why do we need it???
        // To set the Transactions  attributes

        Book book= bookRepository.findById(bookId).get();

        Card card=cardRepositories.findById(cardId).get();

        //Final Goal is to make a transaction Entity, Ste its attribute
        //And Save it.

        Transactions transaction =new Transactions();

        //Setting the attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        //check for validations
        if(book==null || book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepositories.save(transaction);
            throw new Exception("Book is not available");
        }

        if(card==null || (card.getCardStatus()!=CardStatus.ACTIVATED)){

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepositories.save(transaction);
            throw new Exception("card is not valid");
        }

        //We have reached a success case now

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //Set attributes of book
        book.setIssued(true);

        //between the book and transaction : bidirectional
        List<Transactions> listOfTransactionsForBook=book.getListOfTransactions();
        listOfTransactionsForBook.add(transaction);
        book.setListOfTransactions(listOfTransactionsForBook);


        //I need to make changes in the card
        //Book and the card
        List<Book> issuedBookForCard=card.getBooksIssued();
        issuedBookForCard.add(book);
        card.setBooksIssued(issuedBookForCard);


        //Card and the transaction : Bidirectional(parent class)
        List<Transactions> transactionsListForCard=card.getTransactionsList();
        transactionsListForCard.add(transaction);
        card.setTransactionsList(transactionsListForCard);


        //Save the parent.
        cardRepositories.save(card);

        //automatically by cascading : Book and transaction will be saved.


        return "Book issued successfully";
    }

    public String getTransactions(int bookId,int cardId){

        List<Transactions> transactionsList = transactionRepositories.getTransactionsForBookAndCard(bookId,cardId);

        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;
    }

}
