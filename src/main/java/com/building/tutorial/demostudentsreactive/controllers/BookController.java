package com.building.tutorial.demostudentsreactive.controllers;

import com.building.tutorial.demostudentsreactive.models.request.RequestBook;
import com.building.tutorial.demostudentsreactive.models.response.ResponseBook;
import com.building.tutorial.demostudentsreactive.services.BookService;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Single<ResponseEntity<String>> add(@Valid @RequestBody  RequestBook requestBook) {
        return bookService.add(requestBook)
                .map( data -> ResponseEntity.created(URI.create("/author/book"))
                        .body(String.format("Book %s created!", data)))
                .onErrorReturnItem(ResponseEntity.badRequest().body("Couldn't create book"));
    }

    @PutMapping("/update/{id}")
    public Single<ResponseEntity<String>> update(@Valid @PathVariable String id, @RequestBody RequestBook requestBook) {
        requestBook.setId(id);
        return bookService.update(requestBook).toSingle(()
                -> ResponseEntity.ok().body(String.format("Book %s update!", id)))
                .onErrorReturnItem(ResponseEntity.badRequest().body("Couldn't create book"));

    }

    @GetMapping("/all")
    public Single<ResponseEntity<Observable<ResponseBook>>> all() {
        return Single.just(ResponseEntity.ok().body(bookService.getAll()
                .flatMapIterable(x -> x)));
    }

    @GetMapping("/{id}")
    public Single<ResponseEntity<ResponseBook>> getById(@Valid @PathVariable String id){
        return bookService.getById(id).map( c -> ResponseEntity.ok().body(c))
                .onErrorReturnItem(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Single<ResponseEntity<String>> delete(@Valid @PathVariable String id) {
        return bookService.delete(id)
                .toSingle(() -> ResponseEntity.ok(String.format("Book with id %s deleted", id)))
                .onErrorReturnItem(ResponseEntity.badRequest().build());
    }
}
