package com.building.tutorial.demostudentsreactive.controllers;

import com.building.tutorial.demostudentsreactive.models.request.RequestAuthor;
import com.building.tutorial.demostudentsreactive.models.request.RequestBook;
import com.building.tutorial.demostudentsreactive.services.AuthorService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public Single<ResponseEntity<String>> add(@RequestBody RequestAuthor requestAuthor) {
        return authorService.add(requestAuthor)
                .map( data -> ResponseEntity.created(URI.create("/author/add"))
                        .body(String.format("Author %s created!", data)))
                .onErrorReturnItem(ResponseEntity.badRequest().body("Couldn't create author"));
    }
}
