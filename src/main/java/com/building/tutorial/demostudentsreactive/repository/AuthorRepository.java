package com.building.tutorial.demostudentsreactive.repository;

import com.building.tutorial.demostudentsreactive.models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findByName(String name);
}
