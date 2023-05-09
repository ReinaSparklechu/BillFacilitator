package com.electricityBill;

import com.electricityBill.controller.ElectricityBillAppController;
import com.electricityBill.dao.BillDao;
import com.electricityBill.dao.CustomerDao;
import com.electricityBill.dao.DeserializationBillDaoImpl;
import com.electricityBill.dao.DeserializationCustomerDaoImpl;

public class ElectricityBillApp{
//admin login with static username and pass

    public static void main(String[] args) {
        BillDao billDao = DeserializationBillDaoImpl.getInstance();
        CustomerDao customerDao = DeserializationCustomerDaoImpl.getInstance();
        ElectricityBillAppController controller = new ElectricityBillAppController(customerDao,billDao);
        controller.run();
    }
}
