package com.bench.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bench.param.ValidGroup.InsertGroup;
import com.bench.param.ValidGroup.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@TableName("book")
public class Book {

    @TableId("bookid")
    @NotNull(message = "图书id不能为空", groups = {UpdateGroup.class})
    private Integer bookid;

    @NotNull(message = "书名不能为空", groups = {InsertGroup.class})
    private String name;

    @NotNull(message = "作者不能为空", groups = {InsertGroup.class})
    @Length(max = 50, min = 1, message = "作者名长度在[1~50]", groups = {InsertGroup.class, UpdateGroup.class})
    private String author;

    @NotNull(message = "价格不能为空", groups = {InsertGroup.class})
    private Double price;
}
