package com.bench.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("book")
public class Book {
    @TableId("bookid")
    private Integer bookid;
    private String name;
    private String author;
    private Double price;
}
