package com.electricityBill.model;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    private String buildingName;
    private int flatNo;
    private String streetName;
    private String city;

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(int flatNo) {
        this.flatNo = flatNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address(String buildingName, int flatNo, String streetName, String city) {
        this.buildingName = buildingName;
        this.flatNo = flatNo;
        this.streetName = streetName;
        this.city = city;
    }

    public Address() {
        this.buildingName = " ";
        this.flatNo = 0;
        this.streetName = " ";
        this.city = " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getFlatNo() == address.getFlatNo() && Objects.equals(getBuildingName(), address.getBuildingName()) && Objects.equals(getStreetName(), address.getStreetName()) && Objects.equals(getCity(), address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBuildingName(), getFlatNo(), getStreetName(), getCity());
    }

    @Override
    public String toString() {
        return "Address{" +
                "buildingName='" + buildingName + '\'' +
                ", flatNo=" + flatNo +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
