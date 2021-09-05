package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.VehicleDto;
import com.example.bangerandco.model.Vehicle;
import com.example.bangerandco.repository.VehicleRepo;
import com.example.bangerandco.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImplementation implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Override
    public void save(VehicleDto vehicleDto, MultipartFile vehicleImage) throws Exception {
        if (vehicleRepo.findByLicensePlateNumber(vehicleDto.getLicensePlateNumber()) != null) {
            throw new Exception("The vehicle with the license plate number : " + vehicleDto.getLicensePlateNumber() + " already exists in the system!");
        } else if (vehicleImage.isEmpty()) {
            throw new Exception("Please insert an image of the vehicle and try again!");
        } else {
            try {
                Vehicle vehicle = new Vehicle();

                vehicle.setVehicleId(vehicleDto.getVehicleId());
                vehicle.setEngineType(vehicleDto.getEngineType());
                vehicle.setAvailable(vehicleDto.isAvailable());
                vehicle.setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
                vehicle.setModel(vehicleDto.getModel());
                vehicle.setRentPerHour(vehicleDto.getRentPerHour());
                vehicle.setTransmissionType(vehicleDto.getTransmissionType());

                vehicle.setVehicleType(vehicleDto.getVehicleType());

                Vehicle addedVehicle = vehicleRepo.save(vehicle);

                try {
                    String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
                    String vehicleImageNameFormat = "veh" + addedVehicle.getVehicleId() + ".jpg";

                    byte[] vehicleBytes = vehicleImage.getBytes();
                    Path vehiclePath = Paths.get(imagesFolder + vehicleImageNameFormat);
                    Files.write(vehiclePath, vehicleBytes);

                    vehicle.setVehicleImagePath(vehicleImageNameFormat);

                    vehicleRepo.save(vehicle);
                } catch (Exception exception) {
                    throw new Exception("An error occurred while saving the vehicle image! " +
                            "Error " + exception.getMessage() + " . " +
                            "The vehicle has been added successfully!" +
                            "Please update the the added vehicle with an image");
                }
            } catch (Exception exception) {
                throw new Exception(exception);
            }
        }
    }

    @Override
    public List<VehicleDto> category_all() {
        List<VehicleDto> vehicleDtoList = new ArrayList<>();

        for (Vehicle vehicle : vehicleRepo.findAll()) {
            VehicleDto vehicleDto = new VehicleDto();

            vehicleDto.setVehicleId(vehicle.getVehicleId());
            vehicleDto.setEngineType(vehicle.getEngineType());
            vehicleDto.setModel(vehicle.getModel());
            vehicleDto.setRentPerHour(vehicle.getRentPerHour());
            vehicleDto.setTransmissionType(vehicle.getTransmissionType());
            vehicleDto.setVehicleImagePath(vehicle.getVehicleImagePath());

            vehicleDtoList.add(vehicleDto);
        }
        return vehicleDtoList;
    }

    @Override
    public List<VehicleDto> category_type(Long typeId) {
        List<VehicleDto> vehicleDtoList = new ArrayList<>();

        for (Vehicle vehicle : vehicleRepo.findByType(typeId)) {
            VehicleDto vehicleDto = new VehicleDto();

            vehicleDto.setVehicleId(vehicle.getVehicleId());
            vehicleDto.setEngineType(vehicle.getEngineType());
            vehicleDto.setModel(vehicle.getModel());
            vehicleDto.setRentPerHour(vehicle.getRentPerHour());
            vehicleDto.setTransmissionType(vehicle.getTransmissionType());
            vehicleDto.setVehicleImagePath(vehicle.getVehicleImagePath());

            vehicleDtoList.add(vehicleDto);
        }
        return vehicleDtoList;
    }
}
