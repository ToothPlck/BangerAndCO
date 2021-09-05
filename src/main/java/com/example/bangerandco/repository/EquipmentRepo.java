package com.example.bangerandco.repository;

import com.example.bangerandco.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {

    List<Equipment> findByEquipmentIdentifier(String equipmentIdentifier);

    List<Equipment> findByEquipmentType(String type);
}
