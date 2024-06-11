package com.bench.h2.service.impl;

import com.bench.h2.dao.BookRepository;
import com.bench.h2.entity.Book;
import com.bench.h2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookDao;

    @Override
    public void addBook(Book book) {
        bookDao.save(book);
    }

    @Override
    public List<Book> list() {
        return bookDao.findAll();
    }
}
