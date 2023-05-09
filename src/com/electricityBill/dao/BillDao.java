package com.electricityBill.dao;

import com.electricityBill.model.Bill;

import java.util.List;

public interface BillDao {
    public List<Bill> getBillsByCustID(int id);
    public List<Bill> getAll();
    public void addBill(Bill bill);
    public void deleteAll();
}
