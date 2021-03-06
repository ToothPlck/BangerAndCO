package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.VehicleTypeDto;
import com.example.bangerandco.model.VehicleType;
import com.example.bangerandco.repository.VehicleTypeRepo;
import com.example.bangerandco.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleTypeServiceImplementation implements VehicleTypeService {

    @Autowired
    private VehicleTypeRepo vehicleTypeRepo;

    @Override
    public void save(VehicleTypeDto vehicleTypeDto, MultipartFile vehicleTypeImage) throws Exception {
        if (vehicleTypeRepo.findByVehicleType(vehicleTypeDto.getVehicleType()) != null) {
            throw new Exception("The vehicle type : " + vehicleTypeDto.getVehicleType() + " already exists!");
        } else if (vehicleTypeImage.isEmpty()) {
            throw new Exception("Please insert an image of the vehicle type and try again!");
        } else {
            VehicleType vehicleType = new VehicleType();

            vehicleType.setVehicleTypeId(vehicleTypeDto.getVehicleTypeId());
            vehicleType.setDescription(vehicleTypeDto.getDescription());
            vehicleType.setVehicleType(vehicleTypeDto.getVehicleType());

            VehicleType addedType = vehicleTypeRepo.save(vehicleType);

            try {
                Path absolutePath = Paths.get(".");
                String imagesFolder = absolutePath + "/src/main/webapp/images/";
                String vehicleTypeImageNameFormat = "type" + addedType.getVehicleTypeId() + ".jpg";

                byte[] vehicleTypeBytes = vehicleTypeImage.getBytes();
                Path vehicleTypePath = Paths.get(imagesFolder + vehicleTypeImageNameFormat);
                Files.write(vehicleTypePath, vehicleTypeBytes);

                vehicleType.setTypeImagePath(vehicleTypeImageNameFormat);

                vehicleTypeRepo.save(vehicleType);
            } catch (Exception exception) {
                throw new Exception("An error occurred while saving the vehicle type image!");
            }
        }
    }

    @Override
    public List<VehicleTypeDto> getAll() {
        List<VehicleTypeDto> vehicleTypeDtoList = new ArrayList<>();

        for (VehicleType vehicleType : vehicleTypeRepo.findAll()) {
            VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();

            vehicleTypeDto.setVehicleType(vehicleType.getVehicleType());
            vehicleTypeDto.setVehicleTypeId(vehicleType.getVehicleTypeId());
            vehicleTypeDto.setTypeImagePath(vehicleType.getTypeImagePath());
            vehicleTypeDto.setDescription(vehicleType.getDescription());

            vehicleTypeDtoList.add(vehicleTypeDto);
        }
        return vehicleTypeDtoList;
    }

    @Override
    public List<VehicleTypeDto> getAllNav() {
        List<VehicleTypeDto> vehicleTypeDtoList = new ArrayList<>();

        for (VehicleType vehicleType : vehicleTypeRepo.findAll()) {
            VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();

            vehicleTypeDto.setVehicleType(vehicleType.getVehicleType());
            vehicleTypeDto.setVehicleTypeId(vehicleType.getVehicleTypeId());

            vehicleTypeDtoList.add(vehicleTypeDto);
        }
        return vehicleTypeDtoList;
    }

    @Override
    public VehicleTypeDto updatable(long vehicleTypeId) {
        VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
        VehicleType vehicleType = vehicleTypeRepo.getById(vehicleTypeId);

        vehicleTypeDto.setVehicleType(vehicleType.getVehicleType());
        vehicleTypeDto.setVehicleTypeId(vehicleType.getVehicleTypeId());
        vehicleTypeDto.setTypeImagePath(vehicleType.getTypeImagePath());
        vehicleTypeDto.setDescription(vehicleType.getDescription());
        vehicleTypeDto.setVehicles(vehicleType.getVehicles());

        return vehicleTypeDto;
    }

    @Override
    public void updateVehicleType(long vehicleTypeId, MultipartFile vehicleTypeImage, VehicleTypeDto vehicleTypeDto) throws Exception {
        try {
            List<VehicleType> vehicleTypesWithTypeName = vehicleTypeRepo.findVehicleTypeByTypeName(vehicleTypeDto.getVehicleType());
            if (vehicleTypesWithTypeName.size() != 0) {
                for (VehicleType vehicleTypeWithTypeName : vehicleTypesWithTypeName) {
                    if (vehicleTypeId != vehicleTypeWithTypeName.getVehicleTypeId()) {
                        throw new Exception("The entered vehicle type :" + vehicleTypeDto.getVehicleType() + " already exists!");
                    }
                }
            }

            VehicleType vehicleType = vehicleTypeRepo.getById(vehicleTypeId);
            vehicleType.setVehicleType(vehicleTypeDto.getVehicleType());
            vehicleType.setDescription(vehicleTypeDto.getDescription());

            if (!vehicleTypeImage.isEmpty()) {
                Path absolutePath = Paths.get(".");
                String imagesFolder = absolutePath + "/src/main/webapp/images/";
                String vehicleTypeImageNameFormat = "type" + vehicleType.getVehicleTypeId() + ".jpg";

                Path deletePath = Paths.get(imagesFolder + vehicleType.getTypeImagePath());
                Files.delete(deletePath);

                byte[] vehicleTypeByte = vehicleTypeImage.getBytes();
                Path vehicleTypePath = Paths.get(imagesFolder + vehicleTypeImageNameFormat);
                Files.write(vehicleTypePath, vehicleTypeByte);

                vehicleType.setTypeImagePath(vehicleTypeImageNameFormat);
            }

            vehicleTypeRepo.save(vehicleType);

        } catch (Exception exception) {
            throw new Exception("An error occurred while updating the vehicle type! Please try again.");
        }
    }

    @Override
    public void deleteVehicleType(long vehicleTypeId) throws Exception {
        try {
            VehicleType vehicleType = vehicleTypeRepo.getById(vehicleTypeId);

            if (vehicleType.getVehicles().size() > 0) {
                throw new Exception("The category cannot be deleted since there are vehicles under this category");
            } else {
                String imagePath = vehicleType.getTypeImagePath();
                vehicleTypeRepo.deleteById(vehicleTypeId);

                if (!vehicleType.getTypeImagePath().isEmpty()) {
                    Path absolutePath = Paths.get(".");
                    String imagesFolder = absolutePath + "/src/main/webapp/images/";
                    Path deletePath = Paths.get(imagesFolder + imagePath);
                    Files.delete(deletePath);
                }
            }
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
}
