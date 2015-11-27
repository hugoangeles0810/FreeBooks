package com.hugoangeles.freebooks.interactor;

import android.content.Context;

import com.hugoangeles.freebooks.api.BookFactoryClient;
import com.hugoangeles.freebooks.api.BookService;
import com.hugoangeles.freebooks.api.CancelableCallback;
import com.hugoangeles.freebooks.config.Constantes;
import com.hugoangeles.freebooks.interactor.callback.BooksSearchCallback;
import com.hugoangeles.freebooks.model.BooksSearch;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Hugo on 26/11/15.
 */
public class BooksSearchInteractor {

    BookService bookService;

    public BooksSearchInteractor() {
        this.bookService = BookFactoryClient.create();
    }

    public void cancelAllRequests() {
        CancelableCallback.cancelAll();
    }

    public void queryBooks(String query, final BooksSearchCallback booksSearchCallback) {
        Call<BooksSearch> booksSearchCall = bookService.listBooks(query);
        booksSearchCall.enqueue(new CancelableCallback<BooksSearch>() {

            @Override
            public void onCancelableResponse(Response<BooksSearch> response) {
                onSuccesQueryBooks(response, booksSearchCallback);
            }

            @Override
            public void onCancelableFailure(Throwable t) {
                onFailureQueryBooks(t, booksSearchCallback);
            }
        });
    }

    private void onSuccesQueryBooks(Response<BooksSearch> response, final BooksSearchCallback booksSearchCallback) {
        BooksSearch booksSearch = response.body();
        if (!booksSearch.getError().equals(Constantes.NO_ERROR)) {
            return;
        }

        if (booksSearch.getBooks() != null && !booksSearch.getBooks().isEmpty()) {
            booksSearchCallback.onBooksFound(booksSearch.getBooks());
        } else {
            booksSearchCallback.onBooksNotFound();
        }
    }

    private void onFailureQueryBooks(Throwable t, final BooksSearchCallback booksSearchCallback) {

    }

}
