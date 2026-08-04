package com.yigit.blog_api.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(SlugAlreadyExistsException.class)
        public ResponseEntity<ApiError> handleSlugAlreadyExistsException(SlugAlreadyExistsException ex,
                        HttpServletRequest request) {
                ApiError apiError = new ApiError(
                                HttpStatus.CONFLICT.value(),
                                HttpStatus.CONFLICT.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI(),
                                LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(apiError);
        }

        @ExceptionHandler(PostNotFoundException.class)
        public ResponseEntity<ApiError> handlePostNotFoundException(PostNotFoundException ex,
                        HttpServletRequest request) {
                ApiError error = new ApiError(
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI(),
                                LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(error);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiError> handleException(
                        Exception ex,
                        HttpServletRequest request) {

                ApiError error = new ApiError(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI(),
                                LocalDateTime.now());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error);
        }
}
