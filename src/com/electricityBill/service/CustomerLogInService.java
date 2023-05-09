package com.electricityBill.service;

import com.electricityBill.dao.CustomerDao;
import com.electricityBill.exception.InvalidCredentialsException;
import com.electricityBill.model.Credentials;
import com.electricityBill.model.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CustomerLogInService {
    private static CustomerDao customerDao;


    public static Credentials login(int customerLogin) throws InvalidCredentialsException {
        if(customerDao.existById(customerLogin)){
            return new Credentials(customerLogin, null, Credentials.CredentialType.CUSTOMER);
        } throw new InvalidCredentialsException();
    }
    public static void setCustomerDao(CustomerDao customerDao){
        CustomerLogInService.customerDao = customerDao;
    }
}
