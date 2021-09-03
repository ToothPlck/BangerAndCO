package com.example.bangerandco.repository;

import com.example.bangerandco.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    Vehicle findByLicensePlateNumber(String licenseNumber);
}
