package com.example.vehiclemanagementsystem.model;

public class InfractionModel {
    public String infractionType;
    public String infractionNumber;
    public String infractionCost;
    public String infractionDate;
    public String infractionLocation;
    public String infractionMunicipal;
    public String infractionEfwaterkoom;

    public InfractionModel() {
    }

    public InfractionModel(String infractionType, String infractionNumber, String infractionCost, String infractionDate,
                           String infractionLocation, String infractionMunicipal, String infractionEfwaterkoom) {
        this.infractionType = infractionType;
        this.infractionNumber = infractionNumber;
        this.infractionCost = infractionCost;
        this.infractionDate = infractionDate;
        this.infractionLocation = infractionLocation;
        this.infractionMunicipal = infractionMunicipal;
        this.infractionEfwaterkoom = infractionEfwaterkoom;
    }

    public String getInfractionCost() {
        return infractionCost;
    }

    public void setInfractionCost(String infractionCost) {
        this.infractionCost = infractionCost;
    }

    public String getInfractionType() {
        return infractionType;
    }

    public void setInfractionType(String infractionType) {
        this.infractionType = infractionType;
    }

    public String getInfractionNumber() {
        return infractionNumber;
    }

    public void setInfractionNumber(String infractionNumber) {
        this.infractionNumber = infractionNumber;
    }

    public String getInfractionDate() {
        return infractionDate;
    }

    public void setInfractionDate(String infractionDate) {
        this.infractionDate = infractionDate;
    }

    public String getInfractionLocation() {
        return infractionLocation;
    }

    public void setInfractionLocation(String infractionLocation) {
        this.infractionLocation = infractionLocation;
    }

    public String getInfractionMunicipal() {
        return infractionMunicipal;
    }

    public void setInfractionMunicipal(String infractionMunicipal) {
        this.infractionMunicipal = infractionMunicipal;
    }

    public String getInfractionEfwaterkoom() {
        return infractionEfwaterkoom;
    }

    public void setInfractionEfwaterkoom(String infractionEfwaterkoom) {
        this.infractionEfwaterkoom = infractionEfwaterkoom;
    }
}
