package com.example.vehiclemanagementsystem.model;

public class HomeModel {
    public String prefixNumber;
    public String vehicleNumber;
    public String registrationNumber;
    public String vehicleOwnerName;

    public HomeModel() {
    }

    public HomeModel(String prefixNumber, String vehicleNumber, String registrationNumber, String vehicleOwnerName) {
        this.prefixNumber = prefixNumber;
        this.vehicleNumber = vehicleNumber;
        this.registrationNumber = registrationNumber;
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public String getPrefixNumber() {
        return prefixNumber;
    }

    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }
}
