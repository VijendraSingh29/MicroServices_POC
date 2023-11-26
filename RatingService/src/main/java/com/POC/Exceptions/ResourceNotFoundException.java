package com.POC.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String s) {
        super(s);
    }

    public ResourceNotFoundException()
    {
        super("Hotel is not found ");
    }
}