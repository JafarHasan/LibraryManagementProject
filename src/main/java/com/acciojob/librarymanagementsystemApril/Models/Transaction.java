package com.acciojob.librarymanagementsystemApril.Models;

import com.acciojob.librarymanagementsystemApril.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date issueDate;

    private Date returnDate;

    private Integer fineAmt;

    @JoinColumn
    @ManyToOne  //many transaction is possible on 1 Card
    private LibraryCard card;

    @JoinColumn
    @ManyToOne  //many transaction is possible on 1 Book
    private Book book;

}
