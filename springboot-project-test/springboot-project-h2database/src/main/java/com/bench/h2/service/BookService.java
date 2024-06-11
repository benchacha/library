package com.bench.h2.service;

import com.bench.h2.entity.Book;

import java.util.List;

public interface BookService {

    public void addBook(Book book);

    public List<Book> list();
}
