package com.electricityBill.dao;

import com.electricityBill.model.Bill;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeserializationBillDaoImpl implements BillDao{
    private static DeserializationBillDaoImpl billDao;
    private static List<Bill> bills;
    private static Map<Integer,List<Bill>> custIDBillMap;
    public static DeserializationBillDaoImpl getInstance(){
        if(billDao == null){
            billDao = new DeserializationBillDaoImpl();
        }
        return billDao;
    }
    private DeserializationBillDaoImpl(){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("BillList.ser")));
            bills = (List<Bill>) inputStream.readObject();
            custIDBillMap = new HashMap<>();
            bills.stream().forEach(bill -> {
                if(custIDBillMap.containsKey(bill.getCustID())){
                    custIDBillMap.get(bill.getCustID()).add(bill);
                }else{
                    custIDBillMap.put(bill.getCustID(), new ArrayList<>());
                    custIDBillMap.get(bill.getCustID()).add(bill);
                }
            });
        } catch (FileNotFoundException e) {
            System.out.println("Starting app for the first time. Creating BillList");
            bills = new ArrayList<>();
            custIDBillMap = new HashMap<>();
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bill> getBillsByCustID(int id) {
        return custIDBillMap.get(id);
    }

    @Override
    public List<Bill> getAll() {
        return bills;
    }

    @Override
    public void addBill(Bill bill) {
        bill.setBillID(bills.size());
        bills.add(bill);
        if(custIDBillMap.containsKey(bill.getCustID())){
            custIDBillMap.get(bill.getCustID()).add(bill);
        }else{
            custIDBillMap.put(bill.getCustID(), new ArrayList<>());
            custIDBillMap.get(bill.getCustID()).add(bill);
        }
        save();
    }

    @Override
    public void deleteAll() {
        bills.clear();
        save();
        custIDBillMap.clear();
    }
    private void save(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(new File("BillList.ser").toPath()))) {
            outputStream.writeObject(bills);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
