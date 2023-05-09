package com.electricityBill.exception;

public class InvalidCredentialsException extends Exception{
    @Override
    public String getMessage() {
        return "Invalid credentials";
    }
}
