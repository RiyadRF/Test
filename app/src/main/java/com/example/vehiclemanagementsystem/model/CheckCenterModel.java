package com.example.vehiclemanagementsystem.model;

public class CheckCenterModel {
    String centerName;
    String centerPhone;
    String centerLocation;

    public CheckCenterModel(String centerName, String centerPhone, String centerLocation) {
        this.centerName = centerName;
        this.centerPhone = centerPhone;
        this.centerLocation = centerLocation;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterPhone() {
        return centerPhone;
    }

    public void setCenterPhone(String centerPhone) {
        this.centerPhone = centerPhone;
    }

    public String getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(String centerLocation) {
        this.centerLocation = centerLocation;
    }
}
