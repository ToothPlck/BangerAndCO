package com.example.bangerandco.service;

import com.example.bangerandco.dto.VehicleTypeDto;

import java.util.List;

public interface VehicleTypeService {

    void save(VehicleTypeDto vehicleTypeDto) throws Exception;

    List<VehicleTypeDto> getAll();
}
