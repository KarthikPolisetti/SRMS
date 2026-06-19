package com.srms.student_result_management.exception;

public class ResourceNotFoundException extends  RuntimeException
{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
