package com.example.demo.usersite.exception;

public class ResourseNotFoundException extends RuntimeException {

    public ResourseNotFoundException() {
        super("리소스를 찾을 수 없습니다.");
    }
}
