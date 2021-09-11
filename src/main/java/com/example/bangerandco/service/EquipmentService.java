package com.example.bangerandco.service;

import com.example.bangerandco.dto.EquipmentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EquipmentService {

    void save(EquipmentDto equipmentDto, MultipartFile equipmentImage) throws Exception;

    List<EquipmentDto> getAll();

    List<EquipmentDto> getByType(String type);

    void updateEquipment(long equipmentId, MultipartFile equipmentImage, EquipmentDto equipmentDto) throws Exception;

    void deleteEquipment(long equipmentId);

    EquipmentDto updatable(long equipmentId);

    List<EquipmentDto> available(String pickDate, String pickTime, String dropDate, String dropTime);
}
