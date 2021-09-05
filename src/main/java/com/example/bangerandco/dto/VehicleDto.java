package com.example.bangerandco.dto;

import com.example.bangerandco.model.Rental;
import com.example.bangerandco.model.VehicleType;

import java.util.List;

public class VehicleDto {
    private long vehicleId;
    private String model;
    private String licensePlateNumber;
    private String engineType;
    private String transmissionType;
    private String rentPerHour;
    private String vehicleImagePath;
    private boolean isAvailable;

    private VehicleType vehicleType;
    List<Rental> rentals;

    public VehicleDto() {
    }

    public VehicleDto(long vehicleId, String model, String licensePlateNumber, String engineType, String transmissionType, String rentPerHour, String vehicleImagePath, boolean isAvailable, VehicleType vehicleType, List<Rental> rentals) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.licensePlateNumber = licensePlateNumber;
        this.engineType = engineType;
        this.transmissionType = transmissionType;
        this.rentPerHour = rentPerHour;
        this.vehicleImagePath = vehicleImagePath;
        this.isAvailable = isAvailable;
        this.vehicleType = vehicleType;
        this.rentals = rentals;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getRentPerHour() {
        return rentPerHour;
    }

    public void setRentPerHour(String rentPerHour) {
        this.rentPerHour = rentPerHour;
    }

    public String getVehicleImagePath() {
        return vehicleImagePath;
    }

    public void setVehicleImagePath(String vehicleImagePath) {
        this.vehicleImagePath = vehicleImagePath;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
