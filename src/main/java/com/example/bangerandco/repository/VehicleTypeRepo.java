package com.example.bangerandco.repository;

import com.example.bangerandco.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepo extends JpaRepository<VehicleType, Long> {

    VehicleType findByVehicleType(String vehicleType);
}
