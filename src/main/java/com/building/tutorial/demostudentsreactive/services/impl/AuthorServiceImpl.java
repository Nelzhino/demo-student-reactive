package com.building.tutorial.demostudentsreactive.services.impl;

import com.building.tutorial.demostudentsreactive.models.Author;
import com.building.tutorial.demostudentsreactive.models.request.RequestAuthor;
import com.building.tutorial.demostudentsreactive.repository.AuthorRepository;
import com.building.tutorial.demostudentsreactive.services.AuthorService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Single<String> add(RequestAuthor requestAuthor) {
        return Single.create( s -> {
            Optional<Author> author = this.authorRepository.findByName(requestAuthor.getName());
            if (author.isEmpty()) {
                Author authorSaved = authorRepository.save(Author.builder()
                        .name(requestAuthor.getName())
                        .build());
                s.onSuccess(authorSaved.getId());
            } else {
                s.onError(new EntityNotFoundException());
            }
        });
    }
}
