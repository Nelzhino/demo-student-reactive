package com.building.tutorial.demostudentsreactive.services;

import com.building.tutorial.demostudentsreactive.models.Author;
import com.building.tutorial.demostudentsreactive.models.request.RequestAuthor;
import io.reactivex.Single;

public interface AuthorService {

    Single<String> add(RequestAuthor requestAuthor);

}
