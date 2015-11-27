package com.hugoangeles.freebooks.interactor.callback;

import com.hugoangeles.freebooks.model.Book;

import java.util.List;

/**
 * Created by Hugo on 26/11/15.
 */
public interface BooksSearchCallback {

    void onBooksFound(List<Book> books);
    void onBooksNotFound();

}
