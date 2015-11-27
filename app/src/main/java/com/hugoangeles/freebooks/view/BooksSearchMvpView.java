package com.hugoangeles.freebooks.view;

import com.hugoangeles.freebooks.model.Book;

import java.util.List;

/**
 * Created by Hugo on 26/11/15.
 */
public interface BooksSearchMvpView extends MvpView {

    void showLoading();

    void hideLoading();

    void renderBooks(List<Book> books);

    void launchBookDetail(Long id);

    void showNoBooksFound();

}
