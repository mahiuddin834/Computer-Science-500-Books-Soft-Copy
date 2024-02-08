package com.itnation.computersciencebooks.ModelClass;

public class AllBooks {

    String bookName, bookDes, bookLink, coverImg;

    public AllBooks() {
    }

    public AllBooks(String bookName, String bookDes, String bookLink, String coverImg) {
        this.bookName = bookName;
        this.bookDes = bookDes;
        this.bookLink = bookLink;
        this.coverImg = coverImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDes() {
        return bookDes;
    }

    public void setBookDes(String bookDes) {
        this.bookDes = bookDes;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
}
