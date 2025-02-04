package com.example.relation.global.exception;

public class FileUploadException extends RuntimeException{
    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException() {
        super("파일 업로드를 실패했습니다.");
    }
}
