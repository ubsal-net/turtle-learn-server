package com.dsu.turtlelearnserver.randomquestion.exception;

public class IllegalQuestionAccessException extends RuntimeException{

    public IllegalQuestionAccessException(final String message) {
        super(message);
    }
}
