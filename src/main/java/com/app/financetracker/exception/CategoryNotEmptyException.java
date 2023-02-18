package com.app.financetracker.exception;

public class CategoryNotEmptyException extends RuntimeException {
    public CategoryNotEmptyException(String message){
        super(message);
    }
}
