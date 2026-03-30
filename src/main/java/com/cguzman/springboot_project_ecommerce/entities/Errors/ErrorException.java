package com.cguzman.springboot_project_ecommerce.entities.Errors;


import java.util.Date;

public class ErrorException {
    private String error;
    private String message;
    private Integer code;
    private Date date;

    public ErrorException() {
        this.date = new Date();
    }

    public ErrorException(String message, Integer code, Date date, String error){
        this();
        this.error = error;
        this.message = message;
        this. code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
