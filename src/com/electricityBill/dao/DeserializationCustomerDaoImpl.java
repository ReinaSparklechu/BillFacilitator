package com.electricityBill.dao;

import com.electricityBill.model.Customer;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeserializationCustomerDaoImpl implements CustomerDao{
    private static Map<Integer, Customer> customerMap;
    private static CustomerDao customerDao;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeserializationCustomerDaoImpl)) return false;
        DeserializationCustomerDaoImpl that = (DeserializationCustomerDaoImpl) o;
        return customerMap.equals(that.customerMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerMap);
    }

    private DeserializationCustomerDaoImpl(){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("CustomerMap.ser")));
            customerMap = (Map<Integer, Customer>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Starting up for the first time, creating customer records");
            this.customerMap = new HashMap<>();
           save();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static CustomerDao getInstance(){
        if(customerDao == null){
            customerDao = new DeserializationCustomerDaoImpl();

        }
        return customerDao;
    }

    @Override
    public Customer getCustomerByID(int id) {
        return customerMap.get(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void addCustomer(Customer customer) {
        customer.setCustID(customerMap.size());
        customerMap.put(customer.getCustID(), customer);
        save();
    }

    @Override
    public void deleteAll() {
        customerMap.clear();
        save();
    }

    @Override
    public void deleteById(int custId) {
        customerMap.remove(custId);
        save();
    }

    @Override
    public boolean existById(int custId) {
        return customerMap.containsKey(custId);
    }

    private void save() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(new File("CustomerMap.ser").toPath()));
            outputStream.writeObject(customerMap);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
