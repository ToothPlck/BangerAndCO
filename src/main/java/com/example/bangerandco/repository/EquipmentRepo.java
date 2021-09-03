package com.example.bangerandco.repository;

import com.example.bangerandco.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {

    Equipment findByEquipmentIdentifier(String equipmentIdentifier);
}
