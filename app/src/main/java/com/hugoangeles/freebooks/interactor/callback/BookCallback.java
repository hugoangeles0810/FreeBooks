package com.hugoangeles.freebooks.interactor.callback;

import com.hugoangeles.freebooks.model.Book;

/**
 * Created by Hugo on 26/11/15.
 */
public interface BookCallback {

    void onBookLoad(Book book);
    void onBookNotLoad();
}
