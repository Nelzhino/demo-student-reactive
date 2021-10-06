package com.building.tutorial.demostudentsreactive.services.impl;

import com.building.tutorial.demostudentsreactive.models.Author;
import com.building.tutorial.demostudentsreactive.models.Book;
import com.building.tutorial.demostudentsreactive.models.request.RequestBook;
import com.building.tutorial.demostudentsreactive.models.response.ResponseBook;
import com.building.tutorial.demostudentsreactive.repository.AuthorRepository;
import com.building.tutorial.demostudentsreactive.repository.BookRepository;
import com.building.tutorial.demostudentsreactive.services.BookService;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Single<String> add(RequestBook requestBook) {
        return Single.create( s -> {
            Optional<Author> author = authorRepository.findById(requestBook.getIdAuthor());
            if(author.isEmpty()){
                s.onError(new EntityNotFoundException());
            } else {
                Book book = this.bookRepository.save(Book.builder()
                        .author(author.get())
                        .descripcion(requestBook.getDescription())
                        .name(requestBook.getName())
                        .build());
                s.onSuccess(book.getId());
            }
        });
    }

    @Override
    public Completable update(RequestBook requestBook) {
        return Completable.create( c -> {
            Optional<Book> optionalBook = bookRepository.findById(requestBook.getId());
            Optional<Author> optionalAuthor = authorRepository.findById(requestBook.getIdAuthor());
            if (optionalBook.isEmpty() || optionalAuthor.isEmpty()) {
                c.onError(new EntityNotFoundException());
            } else {
                Book book = optionalBook.get();
                book.setName(requestBook.getName());
                book.setDescripcion(requestBook.getDescription());
                book.setAuthor(optionalAuthor.get());
                bookRepository.save(book);
                c.onComplete();
            }
        });
    }

    @Override
    public Observable<List<ResponseBook>> getAll() {
        return Observable.just(
                bookRepository.findAll().stream()
                        .map( book -> {
                    return ResponseBook.builder()
                            .id(book.getId())
                            .name(book.getName())
                            .description(book.getDescripcion())
                            .author(book.getAuthor())
                            .build();
                }).collect(Collectors.toList())
        );

    }

    @Override
    public Single<ResponseBook> getById(String id) {
        return Single.create( s -> {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if(optionalBook.isEmpty()) {
                s.onError(new EntityNotFoundException());
            } else {
                Book book = optionalBook.get();
                s.onSuccess(
                        ResponseBook.builder()
                                .id(book.getId())
                                .author(book.getAuthor())
                                .description(book.getDescripcion())
                                .name(book.getName())
                                .build());
            }
        });
    }

    @Override
    public Completable delete(String id) {
        return Completable.create( c -> {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if(optionalBook.isEmpty()) {
                c.onError(new EntityNotFoundException());
            } else {
                bookRepository.deleteById(id);
                c.onComplete();
            }
        });
    }
}
