package com.acciojob.librarymanagementsystemApril.Repositories;

import com.acciojob.librarymanagementsystemApril.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {

    //UUID is a PK and its an String type

    // Define a custom query method to find a transaction by book ID
    @Query("SELECT t FROM Transaction t WHERE t.book.bookId = :bookId")
    Transaction findByBookId(@Param("bookId") Integer bookId);
}
