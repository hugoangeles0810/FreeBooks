package com.hugoangeles.freebooks.api;

import com.hugoangeles.freebooks.model.Book;
import com.hugoangeles.freebooks.model.BooksSearch;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Hugo on 26/11/15.
 */
public interface BookService {

    @GET("/v1/search/{query}")
    Call<BooksSearch> listBooks(@Path("query") String query);

    @GET("/v1/book/{id}")
    Call<Book> getBook(@Path("id") Long id);

}
