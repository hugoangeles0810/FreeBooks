package com.hugoangeles.freebooks.presenter;

import com.hugoangeles.freebooks.api.CancelableCallback;
import com.hugoangeles.freebooks.config.Constantes;
import com.hugoangeles.freebooks.interactor.BooksSearchInteractor;
import com.hugoangeles.freebooks.interactor.callback.BooksSearchCallback;
import com.hugoangeles.freebooks.model.Book;
import com.hugoangeles.freebooks.view.BooksSearchMvpView;

import java.util.List;

/**
 * Created by Hugo on 26/11/15.
 */
public class BooksSearchSearchPresenter implements Presenter<BooksSearchMvpView>, BooksSearchCallback {

    public static final String TAG = BooksSearchSearchPresenter.class.getSimpleName();

    private BooksSearchMvpView booksSearchMvpView;
    private BooksSearchInteractor booksSearchInteractor;

    @Override
    public void attachedView(BooksSearchMvpView view) {
        if (view == null)
            throw new IllegalArgumentException("You can't set a null view");

        booksSearchMvpView = view;
        booksSearchInteractor = new BooksSearchInteractor();
    }

    @Override
    public void detachView() {
        booksSearchInteractor.cancelAllRequests();
        booksSearchMvpView = null;
    }

    public void onSearchBooks(String query) {
        booksSearchMvpView.showLoading();
        if (query.trim().equals("")) query = Constantes.DEFAULT_QUERY;
        booksSearchInteractor.queryBooks(query, this);
    }

    public void launchBookDetail(Long id) {
        booksSearchMvpView.launchBookDetail(id);
    }

    @Override
    public void onBooksFound(List<Book> books) {
        booksSearchMvpView.hideLoading();
        booksSearchMvpView.renderBooks(books);
    }

    @Override
    public void onBooksNotFound() {
        booksSearchMvpView.hideLoading();
        booksSearchMvpView.showNoBooksFound();
    }
}
