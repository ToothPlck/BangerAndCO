package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.EquipmentDto;
import com.example.bangerandco.dto.VehicleDto;
import com.example.bangerandco.model.Equipment;
import com.example.bangerandco.model.Rental;
import com.example.bangerandco.model.Vehicle;
import com.example.bangerandco.repository.EquipmentRepo;
import com.example.bangerandco.repository.RentalRepo;
import com.example.bangerandco.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImplementation implements EquipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private RentalRepo rentalRepo;

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
    public EquipmentDto updatable(long equipmentId) {
        EquipmentDto equipmentDto = new EquipmentDto();
        Equipment equipment = equipmentRepo.getById(equipmentId);

        equipmentDto.setEquipmentId(equipment.getEquipmentId());
        equipmentDto.setEquipmentIdentifier(equipment.getEquipmentIdentifier());
        equipmentDto.setEquipmentImagePath(equipment.getEquipmentImagePath());
        equipmentDto.setEquipmentType(equipment.getEquipmentType());
        equipmentDto.setEquipmentRentPerHour(equipment.getEquipmentRentPerHour());
        equipmentDto.setAvailable(equipment.isAvailable());
        equipmentDto.setEquipmentName(equipment.getEquipmentName());

        return equipmentDto;
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
            throw new Exception("exception" + exception);
        }
    }

    @Override
    public void deleteEquipment(long equipmentId) throws Exception {
        try {
            Equipment equipment = equipmentRepo.getById(equipmentId);

            for (Rental rentalsInDatabase : rentalRepo.findAllByStatusForDeleteEquipment("pending", "approved")) {
                if (rentalsInDatabase.getEquipment().contains(equipment)) {
                    if (rentalsInDatabase.getRentalReturnDate().toLocalDate().isAfter(LocalDate.now()))
                        throw new Exception("The equipment cannot be deleted due to upcoming bookings which has rented the equipment!");
                    if (rentalsInDatabase.getRentalReturnDate().toLocalDate().isEqual(LocalDate.now()))
                        throw new Exception("The equipment cannot be deleted due to upcoming bookings which has rented the equipment!");
                }
            }
            String imagePath = equipment.getEquipmentImagePath();
            equipmentRepo.deleteById(equipmentId);

            if (!equipment.getEquipmentImagePath().isEmpty()) {
                String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
                Path deletePath = Paths.get(imagesFolder + imagePath);
                Files.delete(deletePath);
            }
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public List<EquipmentDto> available(String pickDate, String pickTime, String dropDate, String dropTime) {
        List<EquipmentDto> equipmentDtoList = new ArrayList<>();
        List<Equipment> allEquipments = equipmentRepo.findAll();
        List<Equipment> unavailableEquipments = new ArrayList<>();
        List<Equipment> availableEquipments = new ArrayList<>();

        List<Rental> rentalsDuringPeriod = rentalRepo.findAll();
        if (rentalsDuringPeriod.size() != 0) {
            for (Rental rentalDuringPeriod : rentalsDuringPeriod) {
                if (LocalDate.parse(pickDate).isBefore(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isAfter(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    if (rentalDuringPeriod.getEquipment().size() != 0) {
                        unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                    }
                } else if (LocalDate.parse(pickDate).isAfter(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(pickDate).isBefore(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    if (rentalDuringPeriod.getEquipment().size() != 0) {
                        unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                    }
                } else if (LocalDate.parse(dropDate).isAfter(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isBefore(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())) {
                    if (rentalDuringPeriod.getEquipment().size() != 0) {
                        unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                    }
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isAfter(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    if (rentalDuringPeriod.getEquipment().size() != 0) {
                        unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                    }
                } else if (LocalDate.parse(pickDate).isBefore(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    if (rentalDuringPeriod.getEquipment().size() != 0) {
                        unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                    }
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    if (rentalDuringPeriod.getEquipment().size() != 0) {
                        unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                    }
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())) {
                    if (LocalTime.parse(pickTime).isAfter(rentalDuringPeriod.getRentalCollectionTime())
                            ||
                            LocalTime.parse(dropTime).isAfter(rentalDuringPeriod.getRentalCollectionTime())) {
                        if (rentalDuringPeriod.getEquipment().size() != 0) {
                            unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                        }
                    }
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    if (LocalTime.parse(pickTime).isBefore(rentalDuringPeriod.getRentalReturnTime())
                            ||
                            LocalTime.parse(dropTime).isBefore(rentalDuringPeriod.getRentalReturnTime())) {
                        if (rentalDuringPeriod.getEquipment().size() != 0) {
                            unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                        }
                    }
                } else if (LocalDate.parse(pickDate).isBefore(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isEqual(rentalDuringPeriod.getRentalCollectionDate().toLocalDate())) {
                    if (LocalTime.parse(dropTime).isAfter(rentalDuringPeriod.getRentalReturnTime())) {
                        if (rentalDuringPeriod.getEquipment().size() != 0) {
                            unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                        }
                    }
                } else if (LocalDate.parse(pickDate).isEqual(rentalDuringPeriod.getRentalReturnDate().toLocalDate())
                        &&
                        LocalDate.parse(dropDate).isAfter(rentalDuringPeriod.getRentalReturnDate().toLocalDate())) {
                    if (rentalDuringPeriod.getEquipment().size() != 0) {
                        unavailableEquipments.addAll(rentalDuringPeriod.getEquipment());
                    }
                }
            }
        }
        if (allEquipments.size() != 0) {
            for (Equipment equipment : allEquipments) {
                if (unavailableEquipments.size() != 0) {
                    if (!unavailableEquipments.contains(equipment)) {
                        availableEquipments.add(equipment);
                    }
                } else {
                    availableEquipments = allEquipments;
                }
            }
        }

        if (availableEquipments.size() != 0) {
            for (Equipment availableEquipment : availableEquipments) {
                if (availableEquipment.isAvailable()) {
                    EquipmentDto equipmentDto = new EquipmentDto();

                    equipmentDto.setEquipmentId(availableEquipment.getEquipmentId());
                    equipmentDto.setEquipmentIdentifier(availableEquipment.getEquipmentIdentifier());
                    equipmentDto.setEquipmentImagePath(availableEquipment.getEquipmentImagePath());
                    equipmentDto.setEquipmentType(availableEquipment.getEquipmentType());
                    equipmentDto.setEquipmentRentPerHour(availableEquipment.getEquipmentRentPerHour());
                    equipmentDto.setAvailable(availableEquipment.isAvailable());
                    equipmentDto.setEquipmentName(availableEquipment.getEquipmentName());

                    equipmentDtoList.add(equipmentDto);
                }
            }
        }

        return equipmentDtoList;
    }

}
