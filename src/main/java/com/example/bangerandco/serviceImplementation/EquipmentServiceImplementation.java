package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.EquipmentDto;
import com.example.bangerandco.model.Equipment;
import com.example.bangerandco.repository.EquipmentRepo;
import com.example.bangerandco.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EquipmentServiceImplementation implements EquipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Override
    public void save(EquipmentDto equipmentDto, MultipartFile equipmentImage) throws Exception {
        if (equipmentRepo.findByEquipmentIdentifier(equipmentDto.getEquipmentIdentifier()) != null) {
            throw new Exception("An equipment already exists with the provided equipment identifier : " + equipmentDto.getEquipmentIdentifier() + "! Please try again with a different equipment identifier!");
        } else if (equipmentImage.isEmpty()) {
            throw new Exception("Please insert an image of the equipment and try again!");
        } else {
            try {
                Equipment equipment = new Equipment();

                equipment.setEquipmentId(equipmentDto.getEquipmentId());
                equipment.setEquipmentRentPerDay(equipmentDto.getEquipmentRentPerDay());
                equipment.setEquipmentType(equipmentDto.getEquipmentType());
                equipment.setAvailable(equipmentDto.isAvailable());
                equipment.setEquipmentName(equipmentDto.getEquipmentName());
                equipment.setEquipmentIdentifier(equipmentDto.getEquipmentIdentifier());

                Equipment addedEquipment = equipmentRepo.save(equipment);

                try {
                    String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
                    String equipmentImageNameFormat = "eq" + addedEquipment.getEquipmentId() + ".jpg";

                    byte[] equipmentBytes = equipmentImage.getBytes();
                    Path equipmentPath = Paths.get(imagesFolder + equipmentImageNameFormat);
                    Files.write(equipmentPath, equipmentBytes);

                    equipment.setEquipmentImagePath(equipmentImageNameFormat);

                    equipmentRepo.save(equipment);
                } catch (Exception exception) {
                    throw new Exception("An error occurred while saving the provided image!" +
                            "Error : " + exception.getMessage() + " ." +
                            "The equipment has been added successfully!" +
                            "Please update the added equipment (" + equipmentDto.getEquipmentIdentifier() + ") with an image!");
                }
            } catch (Exception exception) {
                throw new Exception(exception);
            }
        }
    }
}
