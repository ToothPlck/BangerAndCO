package com.example.bangerandco.service;

import com.example.bangerandco.dto.VehicleDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VehicleService {

    void save(VehicleDto vehicleDto, MultipartFile vehicleImage) throws Exception;

    List<VehicleDto> category_all();

    List<VehicleDto> category_type(Long typeId);
}
