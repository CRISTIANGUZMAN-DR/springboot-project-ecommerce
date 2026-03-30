package com.cguzman.springboot_project_ecommerce.exceptions;

public class RegistryNotFoundException extends RuntimeException {
    public RegistryNotFoundException(String message){
        super(message);
    }
}
