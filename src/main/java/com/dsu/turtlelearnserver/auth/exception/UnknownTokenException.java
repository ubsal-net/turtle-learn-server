package com.dsu.turtlelearnserver.auth.exception;

public class UnknownTokenException extends IllegalArgumentException{

  public UnknownTokenException(String message) {
    super(message);
  }
}
