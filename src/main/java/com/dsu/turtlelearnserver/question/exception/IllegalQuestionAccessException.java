package com.dsu.turtlelearnserver.question.exception;

public class IllegalQuestionAccessException extends RuntimeException{

    public IllegalQuestionAccessException(final String message) {
        super(message);
    }
}
