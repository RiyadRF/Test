

package com.example.vehiclemanagementsystem.model;

public class VehicleModel {

    public String vehicleOwnerName;
    public String address;
    public String vehicleType;
    public String color;
    public String vehicleClass;
    public String vehicleGroup;
    public String adjectiveUse;
    public String manufacturingYear;
    public String day;
    public String month;
    public String year;
    public String insuranceDay;
    public String insuranceMonth;
    public String insuranceYear;
    public String prefixNumber;
    public String vehicleNumber;
    public String registrationNumber;
    public String registrationAttribute;
    public String licensingCenter;
    public String insurance;
    public String chassisNumber;
    public String engineNumber;
    public String engineSize;
    public String fuel;
    public String weight;
    public String polisaNumber;
    public String passengers;
    public String insuranceCompany;



    public VehicleModel() {
    }


    public VehicleModel(String prefixNumber, String vehicleNumber, String registrationNumber, String vehicleOwnerName) {
        this.prefixNumber = prefixNumber;
        this.vehicleNumber = vehicleNumber;
        this.registrationNumber = registrationNumber;
        this.vehicleOwnerName = vehicleOwnerName;
    }


    public VehicleModel(String vehicleOwnerName, String address, String vehicleType, String color, String vehicleClass, String vehicleGroup,
                        String adjectiveUse, String manufacturingYear, String day, String month, String year, String prefixNumber,
                        String vehicleNumber, String registrationNumber, String registrationAttribute, String licensingCenter,
                        String insurance, String chassisNumber, String engineNumber, String engineSize, String fuel, String weight,
                        String polisaNumber, String passengers, String insuranceCompany,String insuranceDay,String insuranceMonth,String insuranceYear) {
        this.vehicleOwnerName = vehicleOwnerName;
        this.address = address;
        this.vehicleType = vehicleType;
        this.color = color;
        this.vehicleClass = vehicleClass;
        this.vehicleGroup = vehicleGroup;
        this.adjectiveUse = adjectiveUse;
        this.manufacturingYear = manufacturingYear;
        this.day = day;
        this.month = month;
        this.year = year;
        this.prefixNumber = prefixNumber;
        this.vehicleNumber = vehicleNumber;
        this.registrationNumber = registrationNumber;
        this.registrationAttribute = registrationAttribute;
        this.licensingCenter = licensingCenter;
        this.insurance = insurance;
        this.chassisNumber = chassisNumber;
        this.engineNumber = engineNumber;
        this.engineSize = engineSize;
        this.fuel = fuel;
        this.weight = weight;
        this.polisaNumber = polisaNumber;
        this.passengers = passengers;
        this.insuranceCompany = insuranceCompany;
        this.insuranceDay = insuranceDay;
        this.insuranceMonth = insuranceMonth;
        this.insuranceYear = insuranceYear;

    }

    public String getInsuranceDay() {
        return insuranceDay;
    }

    public void setInsuranceDay(String insuranceDay) {
        this.insuranceDay = insuranceDay;
    }

    public String getInsuranceMonth() {
        return insuranceMonth;
    }

    public void setInsuranceMonth(String insuranceMonth) {
        this.insuranceMonth = insuranceMonth;
    }

    public String getInsuranceYear() {
        return insuranceYear;
    }

    public void setInsuranceYear(String insuranceYear) {
        this.insuranceYear = insuranceYear;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleGroup() {
        return vehicleGroup;
    }

    public void setVehicleGroup(String vehicleGroup) {
        this.vehicleGroup = vehicleGroup;
    }

    public String getAdjectiveUse() {
        return adjectiveUse;
    }

    public void setAdjectiveUse(String adjectiveUse) {
        this.adjectiveUse = adjectiveUse;
    }

    public String getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getRegistrationAttribute() {
        return registrationAttribute;
    }

    public void setRegistrationAttribute(String registrationAttribute) {
        this.registrationAttribute = registrationAttribute;
    }

    public String getLicensingCenter() {
        return licensingCenter;
    }

    public void setLicensingCenter(String licensingCenter) {
        this.licensingCenter = licensingCenter;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPolisaNumber() {
        return polisaNumber;
    }

    public void setPolisaNumber(String polisaNumber) {
        this.polisaNumber = polisaNumber;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

}

