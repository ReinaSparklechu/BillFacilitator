package com.electricityBill.dao;

import com.electricityBill.model.Bill;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeserializationBillDaoImplTest {

    BillDao billDao = DeserializationBillDaoImpl.getInstance();
    @BeforeEach
    void setUp() {
        billDao.addBill(new Bill(0,0,LocalDate.now(),300, new BigDecimal(0)));
        billDao.addBill(new Bill(1,0,LocalDate.now(),250, new BigDecimal(0)));
        billDao.addBill(new Bill(2,1,LocalDate.now(),150, new BigDecimal(0)));
        billDao.addBill(new Bill(3,0,LocalDate.now(),500, new BigDecimal(0)));
    }

    @AfterEach
    void tearDown() {
        billDao.deleteAll();
    }

    @Test
    void getInstance() {
        assertEquals(billDao, DeserializationBillDaoImpl.getInstance());
    }

    @Test
    void getByCustID() {
        assertEquals(billDao.getBillsByCustID(0).size(),3);
    }

    @Test
    void getAll() {
        assertEquals(billDao.getAll().size(), 4);
    }

    @Test
    void addBill() {
        Bill bill = new Bill(4,2,LocalDate.now(), 342, new BigDecimal(0));
        billDao.addBill(bill);
        assertEquals(billDao.getAll().get(4), bill);
    }
}