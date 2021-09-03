package com.example.bangerandco.service;

import com.example.bangerandco.dto.EquipmentDto;
import org.springframework.web.multipart.MultipartFile;

public interface EquipmentService {

    void save(EquipmentDto equipmentDto, MultipartFile equipmentImage) throws Exception;
}
