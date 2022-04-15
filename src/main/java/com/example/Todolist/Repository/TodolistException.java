package com.example.Todolist.Repository;

import com.example.Todolist.Model.Todolist;

public class TodolistException extends RuntimeException {
    public TodolistException() {
        super();
    }

    public TodolistException(String message) {
        super(message);
    }

    public TodolistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodolistException(Throwable cause) {
        super(cause);
    }

    public TodolistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
