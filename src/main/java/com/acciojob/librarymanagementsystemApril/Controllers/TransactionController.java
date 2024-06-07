package com.acciojob.librarymanagementsystemApril.Controllers;

import com.acciojob.librarymanagementsystemApril.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("issueBook")
    public ResponseEntity issueBook(@RequestParam("cardId")Integer cardId,
                                    @RequestParam("bookId")Integer bookId){
        try{
            String result=transactionService.issuedBook(bookId,cardId);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("returnBook")
    public ResponseEntity returnBook(@RequestParam("bookId")Integer bookId,
                                     @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        try{
            String result=transactionService.returnBook(bookId,date);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
