package com.hugoangeles.freebooks.presenter;

import com.hugoangeles.freebooks.api.CancelableCallback;
import com.hugoangeles.freebooks.interactor.BookInteractor;
import com.hugoangeles.freebooks.interactor.callback.BookCallback;
import com.hugoangeles.freebooks.model.Book;
import com.hugoangeles.freebooks.view.BookMvpView;

/**
 * Created by Hugo on 26/11/15.
 */
public class BookPresenter implements Presenter<BookMvpView>, BookCallback {

    private BookMvpView bookMvpView;
    private BookInteractor bookInteractor;

    @Override
    public void attachedView(BookMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        bookMvpView = view;
        bookInteractor = new BookInteractor();
    }

    @Override
    public void detachView() {
        bookInteractor.cancelAllRequests();
        bookMvpView = null;
    }

    public void loadBook(long id) {
        bookMvpView.showLoader();
        bookInteractor.loadBook(id, this);
    }

    public void downloadBook(String url){
        bookMvpView.downloadBook(url);
    }

    @Override
    public void onBookLoad(Book book) {
        bookMvpView.hideLoader();
        bookMvpView.renderBook(book);
    }

    @Override
    public void onBookNotLoad() {

    }
}
