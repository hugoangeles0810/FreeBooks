package com.hugoangeles.freebooks.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hugo on 26/11/15.
 */
public class BooksSearch implements Serializable {

    @SerializedName("Error")
    private String error;

    @SerializedName("Total")
    private Integer total;

    @SerializedName("Books")
    private List<Book> books;

    public BooksSearch() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
