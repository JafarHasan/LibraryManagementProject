package com.acciojob.librarymanagementsystemApril.Services;


import com.acciojob.librarymanagementsystemApril.Models.Author;
import com.acciojob.librarymanagementsystemApril.Models.Book;
import com.acciojob.librarymanagementsystemApril.Repositories.AuthorRepository;
import com.acciojob.librarymanagementsystemApril.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book){
        book=bookRepository.save(book);
        return "Book has been saved to DB with Book ID="+book.getBookId();
    }

    public String associateBookAndAuthor(Integer bookId,Integer authorId) throws Exception{

        Optional<Book> optionalBook=bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new Exception("Invalid Book Id");
        }

        Optional<Author> optionalAuthor=authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()){
            throw new Exception("Invalid Author Id");
        }

        Book book=optionalBook.get();
        Author author=optionalAuthor.get();

        book.setAuthor(author);//set FK in Db
        author.setNoOfBooks(author.getNoOfBooks()+1); //increment book no by 1

        bookRepository.save(book);
        authorRepository.save(author);

        return "Book and Author have been associated";
    }
}
