package com.electricityBill.dao;

import com.electricityBill.model.Customer;

import java.util.List;

public interface CustomerDao {

    public Customer getCustomerByID(int id);
    public List<Customer> getAllCustomers();
    public void addCustomer(Customer customer);
    public void deleteAll();
    public void deleteById(int custId);
    public boolean existById(int custId);

}
