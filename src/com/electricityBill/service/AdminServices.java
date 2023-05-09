package com.electricityBill.service;

import com.electricityBill.dao.BillDao;
import com.electricityBill.dao.CustomerDao;
import com.electricityBill.exception.CustomerNotExistException;
import com.electricityBill.model.Bill;
import com.electricityBill.model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminServices {
    private BillDao billDao;
    private CustomerDao customerDao;

    public AdminServices(BillDao billDao, CustomerDao customerDao){
        this.billDao = billDao;
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return  customerDao.getAllCustomers();
    }

    public List<Bill> getCustomerBill(int custId) throws CustomerNotExistException {
        if(customerDao.existById(custId)){
            return billDao.getBillsByCustID(custId);
        }else throw new CustomerNotExistException();
    }

    public List<Bill> getAllBills(){
        return billDao.getAll();
    }

    public Map<Customer, List<Bill>> getCustomerToBills(){
        Map<Customer,List<Bill>> customerListMap = new HashMap<>();
        customerDao.getAllCustomers().stream().forEach(customer -> {
            customerListMap.put(customer, new ArrayList<>());
        });
        billDao.getAll().stream().parallel().forEach(bill -> {
            customerListMap.get(customerDao.getCustomerByID(bill.getCustID())).add(bill);
        });
        return customerListMap;
    }
    public void generateBill(int custID, LocalDate date, int unitsConsumed) throws CustomerNotExistException {
        if(customerDao.existById(custID)){
            Bill bill = BillService.generateBill(custID,date,unitsConsumed);
        }else throw new CustomerNotExistException();


    }

}
