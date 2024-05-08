package com.acciojob.librarymanagementsystemApril.Services;
import com.acciojob.librarymanagementsystemApril.Enum.TransactionStatus;
import com.acciojob.librarymanagementsystemApril.Models.Book;
import com.acciojob.librarymanagementsystemApril.Models.LibraryCard;
import com.acciojob.librarymanagementsystemApril.Models.Transaction;
import com.acciojob.librarymanagementsystemApril.Repositories.BookRepository;
import com.acciojob.librarymanagementsystemApril.Repositories.CardRepository;
import com.acciojob.librarymanagementsystemApril.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String issuedBook(int bookId,int cardId) throws Exception{

        //get book entity from bookId
        Book book=bookRepository.findById(bookId).get();

        //get Card entity from cardId
        LibraryCard card=cardRepository.findById(cardId).get();

        //Create txn entity
        Transaction transaction=new Transaction();

        //FAILURE : IF BOOK IS ALREADY ISSuED OR CARD LIMIT (3) IS REACHED
        if(book.isIssued()){
            throw new Exception("Book is already Issued!");
        }
        if(card.getNoOfBooksIssued()==3){
            throw new Exception("Card limit reached!");
        }

        //Success:
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //set Foreign Key entity(Book and Card)
        transaction.setBook(book);
        transaction.setCard(card);

        //set book isIssued=true
        book.setIssued(true);

        //set Card: noOfIssuedBook+1
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);

        //save all to DB
        transaction=transactionRepository.save(transaction);
        book=bookRepository.save(book);
        card=cardRepository.save(card);

        return "The Transaction is saved Id="+transaction.getTransactionId();

    }

    public String returnBook(int bookId){
        //get book Entity from Book
        Book book=bookRepository.findById(bookId).get();

        //create txn Entity and Retrieve the Transaction entity by book ID
        Transaction transaction = transactionRepository.findByBookId(bookId);


        // set isIssue=false;
        book.setIssued(false);
        //save updated book to db
        book=bookRepository.save(book);

        // Set the return date
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date returnDate = null;
        try {
            returnDate = dateFormat.parse("20-MAY-2024 12:20:30");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        transaction.setReturnDate(returnDate);

        //getting issued date
        Date issueDate=transaction.getIssueDate();
        long noOfDays = calculateDaysDifference(issueDate, returnDate);

        //15 days is allowed after this 10rs perday fine
        if(noOfDays>10){
            int fine=(int)(noOfDays-10)*10;
            transaction.setFineAmt(fine);
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        //save Transaction Details to DB
        transaction=transactionRepository.save(transaction);
        return "Book has been return bookId="+book.getBookId();

    }
    public long calculateDaysDifference(Date issueDate,Date returnDate){
        long millSeconds = returnDate.getTime() - issueDate.getTime();
        return millSeconds / (1000 * 60 * 60 * 24); // Convert milliseconds to days
    }
}
