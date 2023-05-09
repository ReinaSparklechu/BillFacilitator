package com.electricityBill.service;

import com.electricityBill.dao.BillDao;
import com.electricityBill.dao.CustomerDao;
import com.electricityBill.exception.BillNotExistException;
import com.electricityBill.model.Bill;

import java.util.List;

public class CustomerServices {
    BillDao billDao;
    public CustomerServices(BillDao billDao){
        this.billDao = billDao;
    }

    public Bill getCustomerBillForMonthYear(int custID, int month, int year) throws BillNotExistException {
        Bill billFound =  billDao.getBillsByCustID(custID)
                .stream()
                .filter(bill -> {
                    return bill.getDateOfBill().getMonthValue() == month && bill.getDateOfBill().getYear() == year;})
                .findFirst()
                .orElseGet(null);
        if (billFound == null){
            throw new BillNotExistException();
        }
        return billFound;
    }
    public List<Bill> getCustomerBills(int custID){
        return billDao.getBillsByCustID(custID);
    }
}
