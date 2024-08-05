package com.example.productservice02072024.advice;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String message){
        super(message);

    }
}
