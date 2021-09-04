package com.example.bangerandco.repository;

import com.example.bangerandco.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    Vehicle findByLicensePlateNumber(String licenseNumber);

    @Query("from Vehicle v where v.vehicleType.vehicleTypeId=:typeId")
    List<Vehicle> findByType(Long typeId);
}
