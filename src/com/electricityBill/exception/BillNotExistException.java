package com.electricityBill.exception;

public class BillNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "Bill for given customer ID, Month and Year does not exist";
    }
}
