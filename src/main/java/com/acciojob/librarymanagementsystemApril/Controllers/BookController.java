package com.acciojob.librarymanagementsystemApril.Controllers;

import com.acciojob.librarymanagementsystemApril.Models.Book;
import com.acciojob.librarymanagementsystemApril.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("book")

public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("add")
    public String addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("/associateBookAndAuthor")
    public ResponseEntity associateBookAndAuthor(@RequestParam("bookId")Integer bookId,
                                                 @RequestParam("authorId")Integer authorId) {
        try {
            String response = bookService.associateBookAndAuthor(bookId, authorId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all-available-books")
    public ResponseEntity<List<String>> getAllAvailableBooks(){
        try{
            List<String> bookList=bookService.getAllAvailableBooks();
            return new ResponseEntity(bookList,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/Book-With-highest-noOfPages")
    public ResponseEntity<String> getBookNameWithHighestNoOfPages(){
        String res=bookService.getBookNameWithHighestNoOfPages();
        return new ResponseEntity(res,HttpStatus.OK);
    }
}
