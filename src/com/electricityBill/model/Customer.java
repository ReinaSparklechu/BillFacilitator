package com.electricityBill.model;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    private int custID;
    private String custName;
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getCustID() == customer.getCustID() && Objects.equals(getCustName(), customer.getCustName()) && Objects.equals(getAddress(), customer.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustID(), getCustName(), getAddress());
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Customer(int custID, String custName, Address address) {
        this.custID = custID;
        this.custName = custName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custID=" + custID +
                ", custName='" + custName + '\'' +
                ", address=" + address +
                '}';
    }

    public Customer(){
        this.custID = 0;
        this.custName = " ";
        this.address = new Address();
    }
}
