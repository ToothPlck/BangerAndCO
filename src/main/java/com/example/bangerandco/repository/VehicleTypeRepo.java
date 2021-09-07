package com.example.bangerandco.repository;

import com.example.bangerandco.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypeRepo extends JpaRepository<VehicleType, Long> {

    VehicleType findByVehicleType(String vehicleType);

    @Query("from VehicleType v where v.vehicleType=:vehicleType")
    List<VehicleType> findVehicleTypeByTypeName(String vehicleType);
}
