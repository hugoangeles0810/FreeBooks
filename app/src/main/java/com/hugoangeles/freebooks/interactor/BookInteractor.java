package com.hugoangeles.freebooks.interactor;

import com.hugoangeles.freebooks.api.BookFactoryClient;
import com.hugoangeles.freebooks.api.BookService;
import com.hugoangeles.freebooks.api.CancelableCallback;
import com.hugoangeles.freebooks.interactor.callback.BookCallback;
import com.hugoangeles.freebooks.model.Book;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Hugo on 26/11/15.
 */
public class BookInteractor {

    BookService bookService;

    public BookInteractor() {
        this.bookService = BookFactoryClient.create();
    }

    public void cancelAllRequests() {
        CancelableCallback.cancelAll();
    }

    public void loadBook(long id, final BookCallback bookCallback) {
        Call<Book> bookCall = bookService.getBook(id);
        bookCall.enqueue(new CancelableCallback<Book>() {
            @Override
            public void onCancelableResponse(Response<Book> response) {
                Book book = response.body();
                if (book != null)
                    bookCallback.onBookLoad(book);
                else
                    bookCallback.onBookNotLoad();
            }

            @Override
            public void onCancelableFailure(Throwable t) {
                bookCallback.onBookNotLoad();
            }
        });
    }
}
