package com.electricityBill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Bill implements Serializable {
    private int billID;
    private int custID;
    private LocalDate dateOfBill;
    private int UnitsConsumed;
    private BigDecimal totalBill;

    public Bill() {

    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public LocalDate getDateOfBill() {
        return dateOfBill;
    }

    public void setDateOfBill(LocalDate dateOfBill) {
        this.dateOfBill = dateOfBill;
    }

    public int getUnitsConsumed() {
        return UnitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        UnitsConsumed = unitsConsumed;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;
        Bill bill = (Bill) o;
        return getBillID() == bill.getBillID() && getCustID() == bill.getCustID() && getUnitsConsumed() == bill.getUnitsConsumed() && Objects.equals(getDateOfBill(), bill.getDateOfBill()) && Objects.equals(getTotalBill(), bill.getTotalBill());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBillID(), getCustID(), getDateOfBill(), getUnitsConsumed(), getTotalBill());
    }

    public Bill(int billID, int custID, LocalDate dateOfBill, int unitsConsumed, BigDecimal totalBill) {
        this.billID = billID;
        this.custID = custID;
        this.dateOfBill = dateOfBill;
        UnitsConsumed = unitsConsumed;
        this.totalBill = totalBill;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billID=" + billID +
                ", custID=" + custID +
                ", dateOfBill=" + dateOfBill +
                ", UnitsConsumed=" + UnitsConsumed +
                ", totalBill=" + totalBill +
                '}';
    }
}
