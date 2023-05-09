package com.electricityBill.service;

import com.electricityBill.model.Bill;
import com.electricityBill.model.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BillService {
    private static BigDecimal[] rates = {new BigDecimal("0.5"), new BigDecimal("0.8"), new BigDecimal("1.5")};
    public static Bill generateBill(int custID , LocalDate date, int unitsConsumed){
        Bill bill = new Bill();
        bill.setCustID(custID);
        bill.setDateOfBill(date);
        bill.setUnitsConsumed(unitsConsumed);
        bill.setTotalBill(calculateTotal(unitsConsumed));
        return bill;
    }
    private static BigDecimal calculateTotal(int units){
        BigDecimal total = new BigDecimal(0);
        while(units > 0){
            if(units >200){
                total = total.add(rates[2].multiply(BigDecimal.valueOf(units-200)));
                units = 200;
            }else if(units > 100){
                total = total.add(rates[1].multiply(BigDecimal.valueOf(units-100)));
                units =100;
            }else{
                total = total.add(rates[0].multiply(BigDecimal.valueOf(units)));
                units = 0;
            }
        }
        return total;
    }
    public static void configureRate(int rateIndex, BigDecimal rate ){
        rates[rateIndex] = rate;
    }
}
