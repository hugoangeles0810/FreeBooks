package com.hugoangeles.freebooks.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hugo on 26/11/15.
 */
public class Book implements Serializable {

    @SerializedName("ID") private Long id;
    @SerializedName("Title") private String title;
    @SerializedName("SubTitle") private String subtitle;
    @SerializedName("Description") private String description;
    @SerializedName("Author") private String author;
    @SerializedName("ISBN") private String ISBN;
    @SerializedName("Page") private  String page;
    @SerializedName("Year") private  String year;
    @SerializedName("Publisher") private String publisher;
    @SerializedName("Image") private String image;
    @SerializedName("Download") private String download;

    public Book() {
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
