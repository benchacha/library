package com.bench.controller;

import com.bench.entity.Book;
import com.bench.param.ResponseResult;
import com.bench.service.BookService;
import com.bench.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bench.param.ValidGroup.InsertGroup;
import com.bench.param.ValidGroup.UpdateGroup;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IRedisService<Book> redisService;

    @Autowired
    private BookService bookService;

    private static final long CACHE_TIMEOUT = 18000L; // 定义缓存过期时间为18000秒

    // 查询图书时，先查询redis缓存
    @GetMapping("/{bookid}")
    public ResponseEntity<ResponseResult<Book>> getBookById(@PathVariable Integer bookid) {
        log.info("查询图书，bookid: {}", bookid);

        // 先查询缓存
        if (redisService.hasKey("book:bookid:" + bookid)) {
            // 缓存中存在该记录，则返回该结果
            System.out.println("缓存中有结果");
            Book book = redisService.get("book:bookid:" + bookid);
            return ResponseEntity.ok(new ResponseResult<>(true, "Book found", book));
        } else {
            System.out.println("缓存中无结果");
            // 缓存中不存在，则查询数据库
            Book book = bookService.getById(bookid);
            if (book != null) {
                // 若数据库中存在，则将数据库中的结果放入到缓存中，并设置有效期为30分钟
                System.out.println("将查询结果添加到缓存中");
                redisService.set("book:bookid:" + book.getBookid(), book, CACHE_TIMEOUT);
                return ResponseEntity.ok(new ResponseResult<>(true, "Book found", book));
            } else {
                // 数据库中也没有结果
                log.warn("没有查询到该图书");
                return ResponseEntity.ok(new ResponseResult<>(false, "Book not found", null));
            }
        }
    }

    // 添加图书
    @PostMapping
    public ResponseEntity<ResponseResult<Void>> addBook(@Validated({InsertGroup.class}) @RequestBody Book book) {
        if (bookService.save(book)) {
            // 如果保存图书成功，缓存中同时添加
            String redisKey = "book:bookid:" + book.getBookid();
            redisService.set(redisKey, book, CACHE_TIMEOUT);
            return ResponseEntity.ok(new ResponseResult<>(true, "Book added successfully", null));
        } else {
            return ResponseEntity.ok(new ResponseResult<>(false, "添加失败", null));
        }
    }

    // 查看所有图书
    @GetMapping
    public ResponseEntity<ResponseResult<List<Book>>> list() {
        List<Book> books = bookService.list();
        return ResponseEntity.ok(new ResponseResult<>(true, "Books retrieved successfully", books));
    }

    // 更新图书
    @PutMapping("/{bookid}")
    public ResponseEntity<ResponseResult<Boolean>> updateBookById(
            @PathVariable Integer bookid, @Validated({UpdateGroup.class})@RequestBody Book book) {
        if (bookService.updateById(book)) {
            // 如果数据库更新成功，更新缓存
            String redisKey = "book:bookid:" + bookid;
            // 删除旧缓存
            redisService.delete(redisKey);
            // 设置新缓存
            redisService.set(redisKey, book, CACHE_TIMEOUT);
            return ResponseEntity.ok(new ResponseResult<>(true, "Book updated successfully", true));
        } else {
            log.warn("更新图书失败，图书不存在: bookid={}", bookid);
            return ResponseEntity.ok(new ResponseResult<>(false, "Book not found", false));
        }
    }

    // 删除图书
    @DeleteMapping("/{bookid}")
    public ResponseEntity<ResponseResult<Void>> deleteBookById(@PathVariable Integer bookid) {
        boolean deleted = bookService.removeById(bookid);
        if (deleted) {
            // 删除缓存中的对应图书
            String redisKey = "book:bookid:" + bookid;
            redisService.delete(redisKey);

            return ResponseEntity.ok(new ResponseResult<>(true, "Book deleted successfully", null));
        } else {
            log.warn("删除图书失败，图书不存在: bookid={}", bookid);
            return ResponseEntity.ok(new ResponseResult<>(false, "Book not found", null));
        }
    }

    // 计算
    @GetMapping("/calculate")
    public ResponseEntity<ResponseResult<List<Double>>> compute(@RequestParam Double num1, @RequestParam Double num2) {
        if (num2 == 0) {
            log.error("除数不能为0！");
            throw new ArithmeticException("除数不能为0");
        }

        List<Double> result = new ArrayList<>();
        result.add(num1 + num2);
        result.add(num1 - num2);
        result.add(num1 * num2);
        result.add(num1 / num2); // 当 num2 为 0 时，Java 会自动抛出 ArithmeticException

        return ResponseEntity.ok(new ResponseResult<>(true, "Calculation successful", result));
    }
}
