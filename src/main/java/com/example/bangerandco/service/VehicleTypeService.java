package com.example.bangerandco.service;

import com.example.bangerandco.dto.VehicleTypeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VehicleTypeService {

    void save(VehicleTypeDto vehicleTypeDto, MultipartFile vehicleTypeImage) throws Exception;

    List<VehicleTypeDto> getAll();

    VehicleTypeDto updatable(long vehicleTypeId);

    void updateVehicleType(long vehicleTypeId, MultipartFile vehicleTypeImage, VehicleTypeDto vehicleTypeDto) throws Exception;

    void deleteVehicleType(long vehicleTypeId) throws Exception;

    List<VehicleTypeDto> getAllNav();
}
