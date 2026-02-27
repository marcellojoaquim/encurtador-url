package com.url_shortener.excetion;

public class BusinessException extends RuntimeException{

    public BusinessException(String msg) {
        super(msg);
    }
}
