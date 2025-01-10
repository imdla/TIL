package com.example.relation.global.exception;

public class DuplicationEntityException extends RuntimeException {
    public DuplicationEntityException(String message) {
        super(message);
    }

    public DuplicationEntityException() {
      super("이미 존재하는 데이터입니다.");
    }
}
