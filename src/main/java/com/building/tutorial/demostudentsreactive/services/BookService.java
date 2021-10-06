package com.building.tutorial.demostudentsreactive.services;

import com.building.tutorial.demostudentsreactive.models.request.RequestBook;
import com.building.tutorial.demostudentsreactive.models.response.ResponseBook;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public interface BookService {

    Single<String> add(RequestBook requestBook);

    Completable update(RequestBook requestBook);

    Observable<List<ResponseBook>> getAll();

    Single<ResponseBook> getById(String id);

    Completable delete(String id);
}
