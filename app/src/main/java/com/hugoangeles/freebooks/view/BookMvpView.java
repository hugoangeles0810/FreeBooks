package com.hugoangeles.freebooks.view;

import com.hugoangeles.freebooks.model.Book;

/**
 * Created by Hugo on 26/11/15.
 */
public interface BookMvpView extends MvpView{

    void renderBook(Book book);

    void showError(String msg);

    void showLoader();

    void hideLoader();

    void downloadBook(String url);

}
