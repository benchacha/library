package com.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bench.entity.Book;
import com.bench.mapper.BookMapper;
import com.bench.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
