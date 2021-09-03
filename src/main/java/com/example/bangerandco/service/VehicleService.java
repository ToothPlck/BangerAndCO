package com.example.bangerandco.service;

import com.example.bangerandco.dto.VehicleDto;
import org.springframework.web.multipart.MultipartFile;

public interface VehicleService {

    void save(VehicleDto vehicleDto, MultipartFile vehicleImage) throws Exception;
}
