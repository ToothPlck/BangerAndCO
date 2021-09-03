package com.example.bangerandco.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentalId;
    private Date rentalCollectionDate;
    private Date rentalReturnDate;
    private LocalTime rentalCollectionTime;
    private LocalTime rentalReturnTime;
    private String total;
    private String status;
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "equipment_rental",
            joinColumns = {@JoinColumn(name = "rental_id")},
            inverseJoinColumns = {@JoinColumn(name = "equipment_id")}
    )
    List<Equipment> equipment;

    public Rental() {
    }

    public Rental(long rentalId, Date rentalCollectionDate, Date rentalReturnDate, String total, String status, Date createdDate, LocalTime rentalCollectionTime, LocalTime rentalReturnTime, User user, Vehicle vehicle, List<Equipment> equipment) {
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

    public Date getRentalCollectionDate() {
        return rentalCollectionDate;
    }

    public void setRentalCollectionDate(Date rentalCollectionDate) {
        this.rentalCollectionDate = rentalCollectionDate;
    }

    public Date getRentalReturnDate() {
        return rentalReturnDate;
    }

    public void setRentalReturnDate(Date rentalReturnDate) {
        this.rentalReturnDate = rentalReturnDate;
    }

    public LocalTime getRentalCollectionTime() {
        return rentalCollectionTime;
    }

    public void setRentalCollectionTime(LocalTime rentalCollectionTime) {
        this.rentalCollectionTime = rentalCollectionTime;
    }

    public LocalTime getRentalReturnTime() {
        return rentalReturnTime;
    }

    public void setRentalReturnTime(LocalTime rentalReturnTime) {
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
