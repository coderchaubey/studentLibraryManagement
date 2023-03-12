package com.example.Student._Library_Management_System.Model_Layers;

import com.example.Student._Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card {

    //plan is to save this card in db.
    //before saving I have to set its attributes:Rule no.1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  //its auto generated

    @CreationTimestamp
    private Date createdOn; //its auto generated

    @UpdateTimestamp
    private Date updatedOn; //its auto generated


    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus; //set this attribute (we did in the service class of student)

    @OneToOne
    @JoinColumn
    private Student studentVariableName; //foreign key attribute used in the bi-directional
    //mapping in the parent class;


//    Card is Parent wrt Book
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book> booksIssued=new ArrayList<>();


    //Connecting Card class to the Transaction
    //BiDirectional Mapping
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transactions> transactionsList= new ArrayList<>(); //it's a good practice to initialize List attribute but we don't need to worry about that as spring boot already creates a bean for the same


    public Card() {
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudentVariableName() {
        return studentVariableName;
    }

    public void setStudentVariableName(Student studentVariableName) {
        this.studentVariableName = studentVariableName;
    }
}
