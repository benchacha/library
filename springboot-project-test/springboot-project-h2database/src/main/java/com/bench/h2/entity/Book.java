package com.bench.h2.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    private int bookId;
    private String bookName;

    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public String getBookName(){
        return bookName;
    }

    public void setBookName(){
        this.bookName=bookName;
    }

}
