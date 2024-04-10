package com.erick.technicaltest.springbootcrudspaceships.exception;

import com.erick.technicaltest.springbootcrudspaceships.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

// This class will be used to handle the exceptions thrown by the application
@RestControllerAdvice
public class GlobalExceptionHandler {

    // This method will handle the ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest webrequest) {
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webrequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
