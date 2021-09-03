package com.example.bangerandco.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long equipmentId;
    private String equipmentName;
    private String equipmentIdentifier;
    private String equipmentType;
    private String equipmentRentPerDay;
    private boolean isAvailable;
    private String equipmentImagePath;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "equipment_rental",
            joinColumns = {@JoinColumn(name = "equipment_id")},
            inverseJoinColumns = {@JoinColumn(name = "rental_id")}
    )
    List<Rental> rentals;

    public Equipment() {
    }

    public Equipment(long equipmentId, String equipmentName, String equipmentIdentifier, String equipmentType, String equipmentRentPerDay, boolean isAvailable, String equipmentImagePath, List<Rental> rentals) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentIdentifier = equipmentIdentifier;
        this.equipmentType = equipmentType;
        this.equipmentRentPerDay = equipmentRentPerDay;
        this.isAvailable = isAvailable;
        this.equipmentImagePath = equipmentImagePath;
        this.rentals = rentals;
    }

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentIdentifier() {
        return equipmentIdentifier;
    }

    public void setEquipmentIdentifier(String equipmentIdentifier) {
        this.equipmentIdentifier = equipmentIdentifier;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentRentPerDay() {
        return equipmentRentPerDay;
    }

    public void setEquipmentRentPerDay(String equipmentRentPerDay) {
        this.equipmentRentPerDay = equipmentRentPerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getEquipmentImagePath() {
        return equipmentImagePath;
    }

    public void setEquipmentImagePath(String equipmentImagePath) {
        this.equipmentImagePath = equipmentImagePath;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
