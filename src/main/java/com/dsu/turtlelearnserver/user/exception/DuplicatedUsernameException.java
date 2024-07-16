package com.dsu.turtlelearnserver.user.exception;

public class DuplicatedUsernameException extends IllegalArgumentException {
    public DuplicatedUsernameException(final String message) {
        super(message);
    }
}