package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.RentalDto;
import com.example.bangerandco.dto.VehicleDto;
import com.example.bangerandco.model.Equipment;
import com.example.bangerandco.model.Rental;
import com.example.bangerandco.model.User;
import com.example.bangerandco.model.Vehicle;
import com.example.bangerandco.repository.RentalRepo;
import com.example.bangerandco.repository.UserRepo;
import com.example.bangerandco.repository.VehicleRepo;
import com.example.bangerandco.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImplementation implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private RentalRepo rentalRepo;
    @Autowired
    private UserRepo userRepo;

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
            vehicleDto.setLicensePlateNumber(vehicle.getLicensePlateNumber());
            vehicleDto.setTransmissionType(vehicle.getTransmissionType());
            vehicleDto.setVehicleImagePath(vehicle.getVehicleImagePath());
            vehicleDto.setVehicleType(vehicle.getVehicleType());

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
            vehicleDto.setLicensePlateNumber(vehicle.getLicensePlateNumber());
            vehicleDto.setTransmissionType(vehicle.getTransmissionType());
            vehicleDto.setVehicleImagePath(vehicle.getVehicleImagePath());
            vehicleDto.setVehicleType(vehicle.getVehicleType());

            vehicleDtoList.add(vehicleDto);
        }
        return vehicleDtoList;
    }

    @Override
    public VehicleDto updatable(long vehicleId) {
        VehicleDto vehicleDto = new VehicleDto();
        Vehicle vehicle = vehicleRepo.getById(vehicleId);

        vehicleDto.setVehicleId(vehicle.getVehicleId());
        vehicleDto.setEngineType(vehicle.getEngineType());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setRentPerHour(vehicle.getRentPerHour());
        vehicleDto.setTransmissionType(vehicle.getTransmissionType());
        vehicleDto.setVehicleImagePath(vehicle.getVehicleImagePath());
        vehicleDto.setVehicleType(vehicle.getVehicleType());
        vehicleDto.setAvailable(vehicle.isAvailable());
        vehicleDto.setLicensePlateNumber(vehicle.getLicensePlateNumber());

        return vehicleDto;
    }

    @Override
    public void updateVehicle(long vehicleId, MultipartFile vehicleImage, VehicleDto vehicleDto) throws Exception {
        try {
            List<Vehicle> vehiclesWithLicense = vehicleRepo.findVehiclesByLicensePlateNumber(vehicleDto.getLicensePlateNumber());
            if (vehiclesWithLicense.size() != 0) {
                for (Vehicle vehicleWithLicense : vehiclesWithLicense) {
                    if (vehicleId != vehicleWithLicense.getVehicleId()) {
                        throw new Exception("The vehicle with the license number already exists in the database!");
                    }
                }
            }
            Vehicle vehicle = vehicleRepo.getById(vehicleId);

            vehicle.setEngineType(vehicleDto.getEngineType());
            vehicle.setAvailable(vehicleDto.isAvailable());
            vehicle.setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
            vehicle.setModel(vehicleDto.getModel());
            vehicle.setRentPerHour(vehicleDto.getRentPerHour());
            vehicle.setTransmissionType(vehicleDto.getTransmissionType());

            vehicle.setVehicleType(vehicleDto.getVehicleType());

            if (!vehicleImage.isEmpty()) {
                String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
                String vehicleImageNameFormat = "veh" + vehicle.getVehicleId() + ".jpg";

                Path deletePath = Paths.get(imagesFolder + vehicle.getVehicleImagePath());
                Files.delete(deletePath);

                byte[] vehiclesBytes = vehicleImage.getBytes();
                Path vehiclePath = Paths.get(imagesFolder + vehicleImageNameFormat);
                Files.write(vehiclePath, vehiclesBytes);

                vehicle.setVehicleImagePath(vehicleImageNameFormat);
            }

            vehicleRepo.save(vehicle);
        } catch (Exception exception) {
            throw new Exception("exception" + exception);
        }
    }

    @Override
    public void deleteVehicle(long vehicleId) throws Exception {
        try {
            Vehicle vehicle = vehicleRepo.getById(vehicleId);

            for (Rental rentalsInDatabase : rentalRepo.findAllByStatusForDeleteEquipment("pending", "approved")) {
                if (rentalsInDatabase.getVehicle().getVehicleId() == vehicleId) {
                    if (rentalsInDatabase.getRentalReturnDate().toLocalDate().isAfter(LocalDate.now()))
                        throw new Exception("The vehicle cannot be deleted due to upcoming bookings which has rented the vehicle!");
                    if (rentalsInDatabase.getRentalReturnDate().toLocalDate().isEqual(LocalDate.now()))
                        throw new Exception("The vehicle cannot be deleted due to upcoming bookings which has rented the vehicle!");
                }
            }
            String imagePath = vehicle.getVehicleImagePath();
            vehicleRepo.deleteById(vehicleId);

            if (!vehicle.getVehicleImagePath().isEmpty()) {
                String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
                Path deletePath = Paths.get(imagesFolder + imagePath);
                Files.delete(deletePath);
            }
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public List<VehicleDto> available(String pickDate, String pickTime, String dropDate, String dropTime, String email) {
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        List<Vehicle> allVehicles = vehicleRepo.findAll();
        List<Vehicle> unavailableVehicles = new ArrayList<>();
        List<Vehicle> availableVehicles = new ArrayList<>();

        List<Rental> rentalsDuringPeriod = rentalRepo.findAll();
        if (rentalsDuringPeriod.size() != 0) {
            for (Rental rentalDuringPeriod : rentalsDuringPeriod) {
                if (LocalDate.parse(pickDate).isBefore(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isAfter(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                } else if (LocalDate.parse(pickDate).isAfter(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(pickDate).isBefore(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                } else if (LocalDate.parse(dropDate).isAfter(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isBefore(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())) {
                    unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isAfter(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                } else if (LocalDate.parse(pickDate).isBefore(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())) {
                    if (LocalTime.parse(pickTime).isAfter(rentalDuringPeriod.getRentalCollectionTime())
                            ||
                            LocalTime.parse(dropTime).isAfter(rentalDuringPeriod.getRentalCollectionTime())) {
                        unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                    }
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    if (LocalTime.parse(pickTime).isBefore(rentalDuringPeriod.getRentalReturnTime())
                            ||
                            LocalTime.parse(dropTime).isBefore(rentalDuringPeriod.getRentalReturnTime())) {
                        unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                    }
                } else if (LocalDate.parse(pickDate).isBefore(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())) {
                    if (LocalTime.parse(dropTime).isAfter(rentalDuringPeriod.getRentalReturnTime())) {
                        unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                    }
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isAfter(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    unavailableVehicles.add(rentalDuringPeriod.getVehicle());
                }
            }
        }
        if (allVehicles.size() != 0) {
            for (Vehicle vehicle : allVehicles) {
                if (unavailableVehicles.size() != 0) {
                    if (!unavailableVehicles.contains(vehicle)) {
                        availableVehicles.add(vehicle);
                    }
                } else {
                    availableVehicles = allVehicles;
                }
            }
        }

        //calculate users age
        User user = userRepo.findUserByEmail(email);
        int userAge = userAge(user.getDateOfBirth());

        if (availableVehicles.size() != 0) {
            for (Vehicle availableVehicle : availableVehicles) {
                if (availableVehicle.isAvailable()) {
                    if (userAge <= 25) {
                        if (availableVehicle.getVehicleType().getVehicleType().equals("Small Town Car")) {
                            VehicleDto vehicleDto = new VehicleDto();

                            vehicleDto.setVehicleId(availableVehicle.getVehicleId());
                            vehicleDto.setEngineType(availableVehicle.getEngineType());
                            vehicleDto.setModel(availableVehicle.getModel());
                            vehicleDto.setRentPerHour(availableVehicle.getRentPerHour());
                            vehicleDto.setTransmissionType(availableVehicle.getTransmissionType());
                            vehicleDto.setVehicleImagePath(availableVehicle.getVehicleImagePath());
                            vehicleDto.setVehicleType(availableVehicle.getVehicleType());

                            vehicleDtoList.add(vehicleDto);
                        }
                    } else {
                        VehicleDto vehicleDto = new VehicleDto();

                        vehicleDto.setVehicleId(availableVehicle.getVehicleId());
                        vehicleDto.setEngineType(availableVehicle.getEngineType());
                        vehicleDto.setModel(availableVehicle.getModel());
                        vehicleDto.setRentPerHour(availableVehicle.getRentPerHour());
                        vehicleDto.setTransmissionType(availableVehicle.getTransmissionType());
                        vehicleDto.setVehicleImagePath(availableVehicle.getVehicleImagePath());
                        vehicleDto.setVehicleType(availableVehicle.getVehicleType());

                        vehicleDtoList.add(vehicleDto);
                    }
                }
            }
        }

        return vehicleDtoList;
    }

    public int userAge(Date dateOfBirth) {
        return Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears();
    }

}