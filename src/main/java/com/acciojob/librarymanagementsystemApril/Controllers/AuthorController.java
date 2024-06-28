package com.acciojob.librarymanagementsystemApril.Controllers;

import com.acciojob.librarymanagementsystemApril.Models.Author;
import com.acciojob.librarymanagementsystemApril.Repositories.AuthorRepository;
import com.acciojob.librarymanagementsystemApril.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")

public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("add")
    public String addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }
    @GetMapping("findAuthorById")
    public ResponseEntity findAuthorById(@RequestParam("Id") Integer authorId){
        try{
            Author authorResponse=authorService.findAuthorById(authorId);
            return new ResponseEntity(authorResponse,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/authorWithMaxNoOfBooks")
    public ResponseEntity<String> authorWithMaxNoOfBooks(){
        String name=authorService.AuthorWithMaxBook();
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
}
