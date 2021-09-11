package com.example.bangerandco.service;

import com.example.bangerandco.dto.VehicleDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VehicleService {

    void save(VehicleDto vehicleDto, MultipartFile vehicleImage) throws Exception;

    List<VehicleDto> category_all();

    List<VehicleDto> category_type(Long typeId);

    VehicleDto updatable(long vehicleId);

    void updateVehicle(long vehicleId, MultipartFile vehicleImage, VehicleDto vehicleDto) throws Exception;

    void deleteVehicle(long vehicleId);

    List<VehicleDto> available(String pickDate, String pickTime, String dropDate, String dropTime);
}
