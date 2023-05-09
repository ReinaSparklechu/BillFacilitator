package com.electricityBill.dao;

import com.electricityBill.model.Address;
import com.electricityBill.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class DeserializationCustomerDaoImplTest {
    CustomerDao customerDao = DeserializationCustomerDaoImpl.getInstance();

    @BeforeEach
    void setup(){
        customerDao.addCustomer(new Customer(0,"James", new Address()));
        customerDao.addCustomer(new Customer(1,"Dave", new Address()));
        customerDao.addCustomer(new Customer(2,"Evelyn",new Address()));
    }
    @org.junit.jupiter.api.Test
    void getInstance() {
        assert (customerDao.equals(DeserializationCustomerDaoImpl.getInstance()));

    }

    @org.junit.jupiter.api.Test
    void getCustomerByID() {
        assertEquals(customerDao.getCustomerByID(1), new Customer(1,"Dave", new Address()));
    }

    @org.junit.jupiter.api.Test
    void getAllCustomers() {
        assertEquals(customerDao.getAllCustomers().size(),3);
    }

    @org.junit.jupiter.api.Test
    void addCustomer() {
        Customer c = new Customer(4,"Jane",new Address());
        customerDao.addCustomer(c);
        assertNotEquals(customerDao.getCustomerByID(4), c);
        assertEquals(customerDao.getCustomerByID(3), c);
    }
    @AfterEach
    void tearDown(){
        customerDao.deleteAll();
    }
}