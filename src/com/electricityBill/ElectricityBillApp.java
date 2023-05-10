package com.electricityBill;

import com.electricityBill.controller.ElectricityBillAppController;
import com.electricityBill.dao.BillDao;
import com.electricityBill.dao.CustomerDao;
import com.electricityBill.dao.DeserializationBillDaoImpl;
import com.electricityBill.dao.DeserializationCustomerDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ElectricityBillApp{
//admin login with static username and pass

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ElectricityBillAppController controller = context.getBean("controller", ElectricityBillAppController.class);
        controller.run();
    }
}
