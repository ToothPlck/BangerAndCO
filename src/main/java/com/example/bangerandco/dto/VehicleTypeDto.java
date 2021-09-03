package com.example.bangerandco.dto;

import com.example.bangerandco.model.Vehicle;

import java.util.List;

public class VehicleTypeDto {
    private long vehicleTypeId;
    private String vehicleType;
    private String description;
    private String typeImagePath;

    List<Vehicle> vehicles;

    public VehicleTypeDto() {
    }

    public VehicleTypeDto(long vehicleTypeId, String vehicleType, String description, String typeImagePath, List<Vehicle> vehicles) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleType = vehicleType;
        this.description = description;
        this.typeImagePath = typeImagePath;
        this.vehicles = vehicles;
    }

    public long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeImagePath() {
        return typeImagePath;
    }

    public void setTypeImagePath(String typeImagePath) {
        this.typeImagePath = typeImagePath;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
