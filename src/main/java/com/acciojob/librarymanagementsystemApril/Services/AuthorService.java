package com.acciojob.librarymanagementsystemApril.Services;

import com.acciojob.librarymanagementsystemApril.Models.Author;
import com.acciojob.librarymanagementsystemApril.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author){
        author=authorRepository.save(author); //return modified obj
        return "Author saved to DB id="+author.getAuthorId();
    }

    public Author findAuthorById(Integer authorId) throws Exception{
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()){
            throw new Exception("Invalid Author ID");
        }
        Author author=optionalAuthor.get();
        return author;
    }
    public String AuthorWithMaxBook(){
        List<Author> authorList=authorRepository.findAll();
        int maxNoOfBooks=0;
        String name="";
        for(Author author:authorList){
            if(author.getNoOfBooks()>maxNoOfBooks){
                name=author.getAuthorName();
                maxNoOfBooks=author.getNoOfBooks();
            }
        }
        return name;
    }
}
