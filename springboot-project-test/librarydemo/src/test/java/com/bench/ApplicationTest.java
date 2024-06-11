package com.bench;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bench.controller.BookController;
import com.bench.entity.Book;
import com.bench.mapper.BookMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
public class ApplicationTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    BookController bookController;
    @Test
    public void test() {
        System.out.println("Spring Boot Test!");
        List<Book> bookList = bookMapper.selectList(null);
        bookList.forEach(System.out::println);

    }
    @Test
    public void addRedis() {
        System.out.println("Test Redis!");
        List<Book> bookList = bookMapper.selectList(new QueryWrapper<>());
        redisTemplate.delete("bookList");
        for (Book book : bookList) {
            redisTemplate.opsForList().rightPush("bookList", book);
        }
        System.out.println("添加成功");
    }

    @Test
    public void showRedis(){
        System.out.println("查询redis");
        // 获取Redis列表的长度
        Long size = redisTemplate.opsForList().size("bookList");
        if (size > 0){
            List<Object> bookList = redisTemplate.opsForList().range("bookList", 0, size - 1);
            bookList.forEach(System.out::println);
        }
        else{
            System.out.println("没有该值");
        }
    }

    @Test
    public void deleteRedis() {
        System.out.println("删除redis");
        if (redisTemplate.delete("bookList")) {
            System.out.println("删除成功");
        } else {
            System.out.println("该数据不存在");
        }
    }
}
