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
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImplementation implements EquipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;

    @Override
    public void save(EquipmentDto equipmentDto, MultipartFile equipmentImage) throws Exception {
        if (equipmentRepo.findByEquipmentIdentifier(equipmentDto.getEquipmentIdentifier()).size() != 0) {
            throw new Exception("An equipment already exists with the provided equipment identifier : " + equipmentDto.getEquipmentIdentifier() + "! Please try again with a different equipment identifier!");
        } else if (equipmentImage.isEmpty()) {
            throw new Exception("Please insert an image of the equipment and try again!");
        } else {
            try {
                Equipment equipment = new Equipment();

                equipment.setEquipmentId(equipmentDto.getEquipmentId());
                equipment.setEquipmentRentPerHour(equipmentDto.getEquipmentRentPerHour());
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

    @Override
    public List<EquipmentDto> getAll() {
        List<EquipmentDto> equipmentDtoList = new ArrayList<>();

        for (Equipment equipment : equipmentRepo.findAll()) {
            EquipmentDto equipmentDto = new EquipmentDto();

            equipmentDto.setEquipmentId(equipment.getEquipmentId());
            equipmentDto.setEquipmentIdentifier(equipment.getEquipmentIdentifier());
            equipmentDto.setEquipmentImagePath(equipment.getEquipmentImagePath());
            equipmentDto.setEquipmentType(equipment.getEquipmentType());
            equipmentDto.setEquipmentRentPerHour(equipment.getEquipmentRentPerHour());
            equipmentDto.setAvailable(equipment.isAvailable());
            equipmentDto.setEquipmentName(equipment.getEquipmentName());

            equipmentDtoList.add(equipmentDto);
        }
        return equipmentDtoList;
    }

    @Override
    public List<EquipmentDto> getByType(String type) {
        List<EquipmentDto> equipmentDtoList = new ArrayList<>();

        for (Equipment equipment : equipmentRepo.findByEquipmentType(type)) {
            EquipmentDto equipmentDto = new EquipmentDto();

            equipmentDto.setEquipmentId(equipment.getEquipmentId());
            equipmentDto.setEquipmentIdentifier(equipment.getEquipmentIdentifier());
            equipmentDto.setEquipmentImagePath(equipment.getEquipmentImagePath());
            equipmentDto.setEquipmentType(equipment.getEquipmentType());
            equipmentDto.setEquipmentRentPerHour(equipment.getEquipmentRentPerHour());
            equipmentDto.setAvailable(equipment.isAvailable());
            equipmentDto.setEquipmentName(equipment.getEquipmentName());

            equipmentDtoList.add(equipmentDto);
        }
        return equipmentDtoList;
    }

    @Override
    public void updateEquipment(long equipmentId, MultipartFile equipmentImage, EquipmentDto equipmentDto) throws Exception {
        try {
            List<Equipment> equipmentsWithIdentifier = equipmentRepo.findByEquipmentIdentifier(equipmentDto.getEquipmentIdentifier());
            if (equipmentsWithIdentifier.size() != 0) {
                for (Equipment equipmentWithIdentifier : equipmentsWithIdentifier) {
                    if (equipmentId != equipmentWithIdentifier.getEquipmentId()) {
                        throw new Exception("Another equipment with the entered identifier exists! Please try again with a different identifier code.");
                    }
                }
            }

            Equipment equipment = equipmentRepo.getById(equipmentId);
            equipment.setEquipmentRentPerHour(equipmentDto.getEquipmentRentPerHour());
            equipment.setAvailable(equipmentDto.isAvailable());
            equipment.setEquipmentName(equipmentDto.getEquipmentName());
            equipment.setEquipmentIdentifier(equipmentDto.getEquipmentIdentifier());

            if (!equipmentImage.isEmpty()) {
                String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
                String equipmentImageNameFormat = "eq" + equipment.getEquipmentId() + ".jpg";

                Path deletePath = Paths.get(imagesFolder + equipment.getEquipmentImagePath());
                Files.delete(deletePath);

                byte[] equipmentBytes = equipmentImage.getBytes();
                Path equipmentPath = Paths.get(imagesFolder + equipmentImageNameFormat);
                Files.write(equipmentPath, equipmentBytes);

                equipment.setEquipmentImagePath(equipmentImageNameFormat);
            }

            equipmentRepo.save(equipment);
        } catch (Exception exception) {
            System.out.println(exception + "\n\n\n\n\n");
            throw new Exception("exception" + exception);
        }
    }

    @Override
    public void deleteEquipment(long equipmentId) {
        //////////////////////////////////////////////
    }
}
