package com.building.tutorial.demostudentsreactive.repository;

import com.building.tutorial.demostudentsreactive.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
