package com.example.bangerandco.service;

import com.example.bangerandco.dto.VehicleTypeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VehicleTypeService {

    void save(VehicleTypeDto vehicleTypeDto, MultipartFile vehicleTypeImage) throws Exception;

    List<VehicleTypeDto> getAll();
}
