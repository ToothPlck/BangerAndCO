package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.VehicleTypeDto;
import com.example.bangerandco.model.VehicleType;
import com.example.bangerandco.repository.VehicleTypeRepo;
import com.example.bangerandco.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleTypeServiceImplementation implements VehicleTypeService {

    @Autowired
    private VehicleTypeRepo vehicleTypeRepo;

    @Override
    public void save(VehicleTypeDto vehicleTypeDto) throws Exception {
        if (vehicleTypeRepo.findByVehicleType(vehicleTypeDto.getVehicleType()) != null) {
            throw new Exception("The vehicle type : " + vehicleTypeDto.getVehicleType() + " already exists!");
        } else {
            VehicleType vehicleType = new VehicleType();

            vehicleType.setVehicleTypeId(vehicleTypeDto.getVehicleTypeId());
            vehicleType.setDescription(vehicleTypeDto.getDescription());
            vehicleType.setVehicleType(vehicleTypeDto.getVehicleType());

            vehicleTypeRepo.save(vehicleType);
        }
    }

    @Override
    public List<VehicleTypeDto> getAll() {
        List<VehicleTypeDto> vehicleTypeDtoList = new ArrayList<>();

        for (VehicleType vehicleType : vehicleTypeRepo.findAll()) {
            VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();

            vehicleTypeDto.setVehicleType(vehicleType.getVehicleType());
            vehicleTypeDto.setVehicleTypeId(vehicleType.getVehicleTypeId());

            vehicleTypeDtoList.add(vehicleTypeDto);
        }
        return vehicleTypeDtoList;
    }
}
