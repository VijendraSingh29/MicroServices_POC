package com.POC.UserService.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException()
    {
       super("Resouce not found in the database");
    }
}
