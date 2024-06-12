package com.bench.interrupt;

import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/12 12:17
 **/

@Data
public class BaseRes<T> {

    public static final int CODE_SUCCESS = 0;

    public static final int CODE_ERROR = -1;

    public int code;

    public String message;

    public T data;

    // 构造函数，用于初始化 BaseRes 对象的 code、message 和 data 字段
    public BaseRes(int code, String message, T data){
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    // 创建一个表示成功且没有数据的 BaseRes 对象
    public static <T> BaseRes<T> success(){
        return new BaseRes<T>(CODE_SUCCESS, "success", null);
    }
    // 创建一个表示成功且包含数据的 BaseRes 对象
    public static <T> BaseRes<T> success(T data){
        return new BaseRes<T>(CODE_SUCCESS, "success", data);
    }
    // 创建一个表示错误且包含错误消息的 BaseRes 对象
    public static <T> BaseRes<T> error(String msg){
        return new BaseRes<T>(CODE_ERROR, msg, null);
    }
}
