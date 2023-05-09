package com.electricityBill.exception;

public class CustomerNotExistException extends Exception{
    @Override
    public String getMessage() {
        return "Customer ID does not currently exist";
    }
}
