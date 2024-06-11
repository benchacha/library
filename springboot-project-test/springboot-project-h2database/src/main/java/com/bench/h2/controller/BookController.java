package com.bench.h2.controller;


import com.bench.h2.entity.Book;
import com.bench.h2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("add")
    public Book add(Book book){
        bookService.addBook(book);
        return book;
    }

    @RequestMapping("list")
    public List<Book> list() {
        return bookService.list();
    }
}
