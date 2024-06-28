package com.acciojob.librarymanagementsystemApril.Repositories;

import com.acciojob.librarymanagementsystemApril.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
