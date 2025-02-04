package com.example.relation.global.exception;

import com.example.relation.global.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage(), "NOT_FOUND"));
    }

    // 존재하지 않는 URL 요청
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NoHandlerFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(
                        "요청한 리소스를 찾을 수 없습니다: " + ex.getRequestURL(),
                        "NOT_FOUND"
                ));
    }

    // 유효하지 않은 입력값 (유효성 검사)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        // 모든 검증 오류 수집
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        "입력값 검증에 실패했습니다.",
                        "INVALID_INPUT",
                        errors

                ));
    }

    // 허용되지 않은 메서드 요청
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.error("method not allowed", "METHOD_NOT_ALLOWED"));

    }

    // 이미 존재하는 데이터
    @ExceptionHandler(DuplicationEntityException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFound(DuplicationEntityException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage(), "DUPLICATE_ENTITY"));
    }

    // 파일 업로드 오류
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ApiResponse<Void>> handleFileUpload(FileUploadException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(ex.getMessage(), "INTERNAL_SERVER_ERROR"));
    }

}