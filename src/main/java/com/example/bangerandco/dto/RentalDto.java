package com.example.bangerandco.dto;

import com.example.bangerandco.model.Equipment;
import com.example.bangerandco.model.User;
import com.example.bangerandco.model.Vehicle;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

public class RentalDto {
    private long rentalId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String rentalCollectionDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String rentalReturnDate;
    private String rentalCollectionTime;
    private String rentalReturnTime;
    private String total;
    private String status;
    private Date createdDate;

    private User user;
    private Vehicle vehicle;
    List<Equipment> equipment;

    public RentalDto() {
    }

    public RentalDto(long rentalId, String rentalCollectionDate, String rentalReturnDate, String rentalCollectionTime, String rentalReturnTime, String total, String status, Date createdDate, User user, Vehicle vehicle, List<Equipment> equipment) {
        this.rentalId = rentalId;
        this.rentalCollectionDate = rentalCollectionDate;
        this.rentalReturnDate = rentalReturnDate;
        this.rentalCollectionTime = rentalCollectionTime;
        this.rentalReturnTime = rentalReturnTime;
        this.total = total;
        this.status = status;
        this.createdDate = createdDate;
        this.user = user;
        this.vehicle = vehicle;
        this.equipment = equipment;
    }

    public long getRentalId() {
        return rentalId;
    }

    public void setRentalId(long rentalId) {
        this.rentalId = rentalId;
    }

    public String getRentalCollectionDate() {
        return rentalCollectionDate;
    }

    public void setRentalCollectionDate(String rentalCollectionDate) {
        this.rentalCollectionDate = rentalCollectionDate;
    }

    public String getRentalReturnDate() {
        return rentalReturnDate;
    }

    public void setRentalReturnDate(String rentalReturnDate) {
        this.rentalReturnDate = rentalReturnDate;
    }

    public String getRentalCollectionTime() {
        return rentalCollectionTime;
    }

    public void setRentalCollectionTime(String rentalCollectionTime) {
        this.rentalCollectionTime = rentalCollectionTime;
    }

    public String getRentalReturnTime() {
        return rentalReturnTime;
    }

    public void setRentalReturnTime(String rentalReturnTime) {
        this.rentalReturnTime = rentalReturnTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
}
