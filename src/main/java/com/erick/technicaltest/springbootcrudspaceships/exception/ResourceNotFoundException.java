package com.erick.technicaltest.springbootcrudspaceships.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This class will be used to handle the exceptions thrown by the application when a resource is not found
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    // If the list is not found, the exception will be thrown
    public ResourceNotFoundException(String resourceName) {
        super(String.format("There is no %s found", resourceName));
        this.resourceName = resourceName;
    }

    // If the ship is not found, the exception will be thrown
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }



}
