package com.example.bangerandco.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    private String driversLicenseNumber;
    private String email;
    private String contact;
    private String password;
    private String userImagePath;
    private String licenseImagePath;
    private String alternateImagePath;
    private String role;
    private boolean isBlacklisted;
    private boolean isReturningCustomer;
    private boolean isVerified;
    private Date dateOfBirth;
    private Date createdDate;
    private Date updatedDate;
    private long bangerScore;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    List<Rental> rentals;

    public User() {
    }

    public User(long userId, String firstName, String lastName, String driversLicenseNumber, String email, String contact, String password, String userImagePath, String licenseImagePath, String alternateImagePath, String role, boolean isBlacklisted, boolean isReturningCustomer, boolean isVerified, Date dateOfBirth, Date createdDate, Date updatedDate, long bangerScore, List<Rental> rentals) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.driversLicenseNumber = driversLicenseNumber;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.userImagePath = userImagePath;
        this.licenseImagePath = licenseImagePath;
        this.alternateImagePath = alternateImagePath;
        this.role = role;
        this.isBlacklisted = isBlacklisted;
        this.isReturningCustomer = isReturningCustomer;
        this.isVerified = isVerified;
        this.dateOfBirth = dateOfBirth;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.bangerScore = bangerScore;
        this.rentals = rentals;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getLicenseImagePath() {
        return licenseImagePath;
    }

    public void setLicenseImagePath(String licenseImagePath) {
        this.licenseImagePath = licenseImagePath;
    }

    public String getAlternateImagePath() {
        return alternateImagePath;
    }

    public void setAlternateImagePath(String alternateImagePath) {
        this.alternateImagePath = alternateImagePath;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBlacklisted() {
        return isBlacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        isBlacklisted = blacklisted;
    }

    public boolean isReturningCustomer() {
        return isReturningCustomer;
    }

    public void setReturningCustomer(boolean returningCustomer) {
        isReturningCustomer = returningCustomer;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public long getBangerScore() {
        return bangerScore;
    }

    public void setBangerScore(long bangerScore) {
        this.bangerScore = bangerScore;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
