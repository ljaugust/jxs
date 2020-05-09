package com.hohoho.entity;

import javax.xml.crypto.Data;

public class ResultFruit<T> {
    private T data;
    private String message;
    private Integer code;


    public ResultFruit() {
    }

    public ResultFruit(T data) {
        this(data, "操作成功", 200);
    }

    public ResultFruit(String message, Integer code) {
        this(null, message, code);
    }

    public ResultFruit(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
