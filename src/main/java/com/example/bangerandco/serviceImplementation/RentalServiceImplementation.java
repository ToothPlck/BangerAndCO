package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.RentalDto;
import com.example.bangerandco.mailHandler.EmailService;
import com.example.bangerandco.model.Equipment;
import com.example.bangerandco.model.Rental;
import com.example.bangerandco.model.User;
import com.example.bangerandco.repository.EquipmentRepo;
import com.example.bangerandco.repository.RentalRepo;
import com.example.bangerandco.repository.UserRepo;
import com.example.bangerandco.repository.VehicleRepo;
import com.example.bangerandco.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalServiceImplementation implements RentalService {

    @Autowired
    private RentalRepo rentalRepo;
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailService emailService;

    @Override
    public List<RentalDto> userOnGoingRentals(String email) {
        List<RentalDto> rentalDtoList = new ArrayList<>();

        for (Rental rental : rentalRepo.findAllByUserEmailAndStatus(email, "on-going")) {
            RentalDto rentalDto = new RentalDto();
            rentalDto.setRentalId(rental.getRentalId());
            rentalDto.setRentalReturnDate(rental.getRentalReturnDate().toString());
            rentalDto.setRentalReturnTime(rental.getRentalReturnTime().toString());
            rentalDto.setVehicle(rental.getVehicle());

            rentalDtoList.add(rentalDto);
        }
        return rentalDtoList;
    }

    @Override
    public String periodInHours(String pickDate, String pickTime, String dropDate, String dropTime) {
        LocalDate startDate = LocalDate.parse(pickDate);
        LocalDate endDate = LocalDate.parse(dropDate);
        long differenceBetweenDates = Period.between(startDate, endDate).getDays();

        LocalTime startTime = LocalTime.parse(pickTime);
        LocalTime endTime = LocalTime.parse(dropTime);
        long differenceBetweenHours = ChronoUnit.MINUTES.between(startTime, endTime);
        long differenceBetweenMinutes = ChronoUnit.MINUTES.between(startTime, endTime) % 60;

        double totalInMinutes = ((differenceBetweenDates * 24) * 60) + differenceBetweenHours + differenceBetweenMinutes;
        double totalInHours = totalInMinutes / 60;

        return String.valueOf(totalInHours);
    }

    @Override
    public void createBooking(String user, String vehicleId, List<String> equipments, String hours, String pickDate, String pickTime, String dropDate, String dropTime, String total) throws Exception {
        long selectedVehicleId = Long.parseLong(vehicleId);
        List<Rental> rentalListInDatabase = rentalRepo.findAll();

        User user1 = userRepo.findUserByEmail(user);
        if (!user1.isVerified()) {
            throw new Exception("The booking cannot be added since the user is not verified!");
        }
        for (Rental rentalInDatabase : rentalListInDatabase) {

            //double check vehicle and equipments availability
            if (LocalDate.parse(pickDate).isBefore(rentalInDatabase.getRentalCollectionDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isAfter(rentalInDatabase.getRentalReturnDate().toLocalDate())) {
                if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                    throw new Exception("The selected vehicle has become unavailable at the last moment!");
                if (rentalInDatabase.getEquipment().size() != 0) {
                    for (String selectedEquipment : equipments) {
                        long selectedEquipmentId = Long.parseLong(selectedEquipment);
                        for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                            if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                        }
                    }
                }
            } else if (LocalDate.parse(pickDate).isAfter(rentalInDatabase.getRentalCollectionDate().toLocalDate())
                    &&
                    LocalDate.parse(pickDate).isBefore(rentalInDatabase.getRentalReturnDate().toLocalDate())) {
                if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                    throw new Exception("The selected vehicle has become unavailable at the last moment!");
                if (rentalInDatabase.getEquipment().size() != 0) {
                    for (String selectedEquipment : equipments) {
                        long selectedEquipmentId = Long.parseLong(selectedEquipment);
                        for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                            if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                        }
                    }
                }
            } else if (LocalDate.parse(dropDate).isAfter(rentalInDatabase.getRentalCollectionDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isBefore(rentalInDatabase.getRentalCollectionDate().toLocalDate())) {
                if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                    throw new Exception("The selected vehicle has become unavailable at the last moment!");
                if (rentalInDatabase.getEquipment().size() != 0) {
                    for (String selectedEquipment : equipments) {
                        long selectedEquipmentId = Long.parseLong(selectedEquipment);
                        for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                            if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                        }
                    }
                }
            } else if (LocalDate.parse(pickDate).isEqual(rentalInDatabase.getRentalCollectionDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isAfter(rentalInDatabase.getRentalReturnDate().toLocalDate())) {
                if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                    throw new Exception("The selected vehicle has become unavailable at the last moment!");
                if (rentalInDatabase.getEquipment().size() != 0) {
                    for (String selectedEquipment : equipments) {
                        long selectedEquipmentId = Long.parseLong(selectedEquipment);
                        for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                            if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                        }
                    }
                }
            } else if (LocalDate.parse(pickDate).isBefore(rentalInDatabase.getRentalCollectionDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isEqual(rentalInDatabase.getRentalReturnDate().toLocalDate())) {
                if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                    throw new Exception("The selected vehicle has become unavailable at the last moment!");
                if (rentalInDatabase.getEquipment().size() != 0) {
                    for (String selectedEquipment : equipments) {
                        long selectedEquipmentId = Long.parseLong(selectedEquipment);
                        for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                            if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                        }
                    }
                }
            } else if (LocalDate.parse(pickDate).isEqual(rentalInDatabase.getRentalCollectionDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isEqual(rentalInDatabase.getRentalReturnDate().toLocalDate())) {
                if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                    throw new Exception("The selected vehicle has become unavailable at the last moment!");
                if (rentalInDatabase.getEquipment().size() != 0) {
                    for (String selectedEquipment : equipments) {
                        long selectedEquipmentId = Long.parseLong(selectedEquipment);
                        for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                            if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                        }
                    }
                }
            } else if (LocalDate.parse(pickDate).isEqual(rentalInDatabase.getRentalCollectionDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isEqual(rentalInDatabase.getRentalCollectionDate().toLocalDate())) {
                if (LocalTime.parse(pickTime).isAfter(rentalInDatabase.getRentalCollectionTime())
                        ||
                        LocalTime.parse(dropTime).isAfter(rentalInDatabase.getRentalCollectionTime())) {
                    if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                        throw new Exception("The selected vehicle has become unavailable at the last moment!");
                    if (rentalInDatabase.getEquipment().size() != 0) {
                        for (String selectedEquipment : equipments) {
                            long selectedEquipmentId = Long.parseLong(selectedEquipment);
                            for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                                if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                    throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                            }
                        }
                    }
                }
            } else if (LocalDate.parse(pickDate).isEqual(rentalInDatabase.getRentalReturnDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isEqual(rentalInDatabase.getRentalReturnDate().toLocalDate())) {
                if (LocalTime.parse(pickTime).isBefore(rentalInDatabase.getRentalReturnTime())
                        ||
                        LocalTime.parse(dropTime).isBefore(rentalInDatabase.getRentalReturnTime())) {
                    if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                        throw new Exception("The selected vehicle has become unavailable at the last moment!");
                    if (rentalInDatabase.getEquipment().size() != 0) {
                        for (String selectedEquipment : equipments) {
                            long selectedEquipmentId = Long.parseLong(selectedEquipment);
                            for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                                if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                    throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                            }
                        }
                    }
                }
            } else if (LocalDate.parse(pickDate).isBefore(rentalInDatabase.getRentalCollectionDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isEqual(rentalInDatabase.getRentalCollectionDate().toLocalDate())) {
                if (LocalTime.parse(dropTime).isAfter(rentalInDatabase.getRentalReturnTime())) {
                    if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                        throw new Exception("The selected vehicle has become unavailable at the last moment!");
                    if (rentalInDatabase.getEquipment().size() != 0) {
                        for (String selectedEquipment : equipments) {
                            long selectedEquipmentId = Long.parseLong(selectedEquipment);
                            for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                                if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                    throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                            }
                        }
                    }
                }
            } else if (LocalDate.parse(pickDate).isEqual(rentalInDatabase.getRentalReturnDate().toLocalDate())
                    &&
                    LocalDate.parse(dropDate).isAfter(rentalInDatabase.getRentalReturnDate().toLocalDate())) {
                if (selectedVehicleId == rentalInDatabase.getVehicle().getVehicleId())
                    throw new Exception("The selected vehicle has become unavailable at the last moment!");
                if (rentalInDatabase.getEquipment().size() != 0) {
                    for (String selectedEquipment : equipments) {
                        long selectedEquipmentId = Long.parseLong(selectedEquipment);
                        for (Equipment rentalInDatabaseEquipment : rentalInDatabase.getEquipment()) {
                            if (selectedEquipmentId == rentalInDatabaseEquipment.getEquipmentId())
                                throw new Exception("The following equipment : " + rentalInDatabaseEquipment.getEquipmentName() + " has become unavailable at the last moment!");
                        }
                    }
                }
            }
        }


        //Double check pickup and drop difference
        double periodInHours = Double.parseDouble(periodInHours(pickDate, pickTime, dropDate, dropTime));
        if (periodInHours > 336)
            throw new Exception("The maximum rental period is two weeks!");
        if (periodInHours < 5) {
            throw new Exception("The minimum rental period is five hours!");
        }

        //place booking
        try {
            Rental rental = new Rental();
            List<Equipment> equipmentList = new ArrayList<>();

            rental.setCreatedDate(Date.valueOf(LocalDate.now()));
            rental.setRentalCollectionDate(Date.valueOf(pickDate));
            rental.setRentalCollectionTime(LocalTime.parse(pickTime));
            rental.setRentalReturnDate(Date.valueOf(dropDate));
            rental.setRentalReturnTime(LocalTime.parse(dropTime));
            rental.setStatus("pending");
            rental.setTotal(total);
            rental.setUser(userRepo.findUserByEmail(user));
            rental.setVehicle(vehicleRepo.getById(Long.parseLong(vehicleId)));

            for (String selectedEquipment : equipments) {
                equipmentList.add(equipmentRepo.getById(Long.parseLong(selectedEquipment)));
            }
            rental.setEquipment(equipmentList);

            rentalRepo.save(rental);

            emailService.createBooking(userRepo.findUserByEmail(user));

        } catch (Exception exception) {
            throw new Exception("An unexpected error occurred while creating the booking! Error : " + exception.getMessage());
        }
    }

    @Override
    public List<RentalDto> status_all() {
        List<RentalDto> rentalDtoList = new ArrayList<>();

        for (Rental rental : rentalRepo.findAll()) {
            RentalDto rentalDto = new RentalDto();

            rentalDto.setRentalId(rental.getRentalId());
            rentalDto.setCreatedDate(rental.getCreatedDate());
            rentalDto.setRentalCollectionDate(rental.getRentalCollectionDate().toString());
            rentalDto.setRentalCollectionTime(rental.getRentalCollectionTime().toString());
            rentalDto.setRentalReturnDate(rental.getRentalReturnDate().toString());
            rentalDto.setRentalReturnTime(rental.getRentalReturnTime().toString());
            rentalDto.setStatus(rental.getStatus());
            rentalDto.setTotal(rental.getTotal());
            rentalDto.setUser(rental.getUser());
            rentalDto.setVehicle(rental.getVehicle());
            rentalDto.setEquipment(rental.getEquipment());

            rentalDtoList.add(rentalDto);
        }
        return rentalDtoList;
    }

    @Override
    public List<RentalDto> status(String status) {
        List<RentalDto> rentalDtoList = new ArrayList<>();

        for (Rental rental : rentalRepo.findAllByStatus(status)) {
            RentalDto rentalDto = new RentalDto();

            rentalDto.setRentalId(rental.getRentalId());
            rentalDto.setCreatedDate(rental.getCreatedDate());
            rentalDto.setRentalCollectionDate(rental.getRentalCollectionDate().toString());
            rentalDto.setRentalCollectionTime(rental.getRentalCollectionTime().toString());
            rentalDto.setRentalReturnDate(rental.getRentalReturnDate().toString());
            rentalDto.setRentalReturnTime(rental.getRentalReturnTime().toString());
            rentalDto.setStatus(rental.getStatus());
            rentalDto.setTotal(rental.getTotal());
            rentalDto.setUser(rental.getUser());
            rentalDto.setVehicle(rental.getVehicle());
            rentalDto.setEquipment(rental.getEquipment());

            rentalDtoList.add(rentalDto);
        }
        return rentalDtoList;
    }

    @Override
    public void changeStatus(String status, long rentalId) {
        Rental rental = rentalRepo.getById(rentalId);
        rental.setStatus(status);
        User user = new User();


        if (status.equals("completed")) {
            if (!rental.getUser().isReturningCustomer()) {
                user = rental.getUser();
                user.setReturningCustomer(true);
                userRepo.save(user);
            }
        }

        rentalRepo.save(rental);

        emailService.changeStatus(status, user);
    }

    @Override
    public List<RentalDto> userAllRentals(String name) {
        List<RentalDto> rentalDtoList = new ArrayList<>();

        for (Rental rental : rentalRepo.findAllByUserEmail(name)) {
            RentalDto rentalDto = new RentalDto();

            rentalDto.setRentalId(rental.getRentalId());
            rentalDto.setCreatedDate(rental.getCreatedDate());
            rentalDto.setRentalCollectionDate(rental.getRentalCollectionDate().toString());
            rentalDto.setRentalCollectionTime(rental.getRentalCollectionTime().toString());
            rentalDto.setRentalReturnDate(rental.getRentalReturnDate().toString());
            rentalDto.setRentalReturnTime(rental.getRentalReturnTime().toString());
            rentalDto.setStatus(rental.getStatus());
            rentalDto.setTotal(rental.getTotal());
            rentalDto.setUser(rental.getUser());
            rentalDto.setVehicle(rental.getVehicle());
            rentalDto.setEquipment(rental.getEquipment());
            rentalDto.setExtended(rental.isExtended());

            rentalDtoList.add(rentalDto);
        }
        return rentalDtoList;
    }

    @Override
    public List<RentalDto> userRentalByStatus(String name, String status) {
        List<RentalDto> rentalDtoList = new ArrayList<>();

        for (Rental rental : rentalRepo.findAllByStatusAndUserEmail(status, name)) {
            RentalDto rentalDto = new RentalDto();

            rentalDto.setRentalId(rental.getRentalId());
            rentalDto.setCreatedDate(rental.getCreatedDate());
            rentalDto.setRentalCollectionDate(rental.getRentalCollectionDate().toString());
            rentalDto.setRentalCollectionTime(rental.getRentalCollectionTime().toString());
            rentalDto.setRentalReturnDate(rental.getRentalReturnDate().toString());
            rentalDto.setRentalReturnTime(rental.getRentalReturnTime().toString());
            rentalDto.setStatus(rental.getStatus());
            rentalDto.setTotal(rental.getTotal());
            rentalDto.setUser(rental.getUser());
            rentalDto.setVehicle(rental.getVehicle());
            rentalDto.setEquipment(rental.getEquipment());
            rentalDto.setExtended(rental.isExtended());

            rentalDtoList.add(rentalDto);
        }
        return rentalDtoList;
    }

    @Override
    public void cancelRental(long rentalId) throws Exception {
        Rental rental = rentalRepo.getById(rentalId);

        if (rental.getRentalCollectionDate().toLocalDate().isAfter(LocalDate.now())) {
            //difference between now and collection time in hours
            long differenceBetweenDates = Period.between(LocalDate.now(), rental.getRentalCollectionDate().toLocalDate()).getDays();
            long differenceBetweenHours = ChronoUnit.MINUTES.between(LocalTime.now(), rental.getRentalCollectionTime());
            long differenceBetweenMinutes = ChronoUnit.MINUTES.between(LocalTime.now(), rental.getRentalCollectionTime()) % 60;

            double totalInMinutes = ((differenceBetweenDates * 24) * 60) + differenceBetweenHours + differenceBetweenMinutes;
            double difference = totalInMinutes / 60;


            if (rental.getStatus().equals("pending")) { //delete if pending
                rentalRepo.deleteById(rentalId);
                emailService.cancelBooking(rental.getUser().getEmail());
            } else if ((rental.getStatus().equals("approved")) && difference > 24.0) { //delete if approved and collection time is not within 24 hours
                rentalRepo.deleteById(rentalId);
            } else if ((rental.getStatus().equals("approved")) && difference < 24.0) { //throw exception if approved and collection time is within 24 hours
                throw new Exception("The booking cannot be cancelled since the collection time is within the next 24 hours");
            } else {
                throw new Exception("The booking cannot be cancelled!");
            }
        } else {
            throw new Exception("The booking cannot be cancelled");
        }
    }

    @Override
    public Rental rentalDetails(long rentalId) {
        return rentalRepo.getById(rentalId);
    }

    @Override
    public RentalDto getRentalById(long rentalId) {
        Rental rental = rentalRepo.getById(rentalId);
        RentalDto rentalDto = new RentalDto();

        rentalDto.setRentalId(rental.getRentalId());
        rentalDto.setCreatedDate(rental.getCreatedDate());
        rentalDto.setRentalCollectionDate(rental.getRentalCollectionDate().toString());
        rentalDto.setRentalCollectionTime(rental.getRentalCollectionTime().toString());
        rentalDto.setRentalReturnDate(rental.getRentalReturnDate().toString());
        rentalDto.setRentalReturnTime(rental.getRentalReturnTime().toString());
        rentalDto.setStatus(rental.getStatus());
        rentalDto.setTotal(rental.getTotal());
        rentalDto.setUser(rental.getUser());
        rentalDto.setVehicle(rental.getVehicle());
        rentalDto.setEquipment(rental.getEquipment());

        return rentalDto;
    }

    @Override
    public void updateBooking(long rentalId, List<String> equipments, String total) throws Exception {
        try {
            Rental rental = rentalRepo.getById(rentalId);

            List<Equipment> rentalEquipments = rental.getEquipment();
            for (String equipment : equipments) {
                rentalEquipments.add(equipmentRepo.getById(Long.parseLong(equipment)));
            }

            double newPrice = Double.parseDouble(rental.getTotal()) + Double.parseDouble(total);

            rental.setEquipment(rentalEquipments);
            rental.setTotal(String.valueOf(newPrice));

            rentalRepo.save(rental);
            emailService.updateBooking(rental.getUser().getEmail());
        } catch (Exception exception) {
            throw new Exception("An error occurred while updating the booking! Please try again.");
        }
    }

    @Override
    public void extendBooking(long rentalId, String extendDropDate, String extendDropTime) throws Exception {
        try {
            Rental rental = rentalRepo.getById(rentalId);

            List<Rental> rentalListInDatabase = rentalRepo.findAll();

            for (Rental rentalInDatabase : rentalListInDatabase) {
                if (rentalInDatabase.getRentalId() != rentalId) {
                    //check vehicle availability
                    if (rentalInDatabase.getVehicle().getVehicleId() == rental.getVehicle().getVehicleId()) {
                        if (rentalInDatabase.getRentalCollectionDate().toLocalDate().isBefore(LocalDate.parse(extendDropDate)))
                            throw new Exception("The booking cannot be extended due to another booking created for the vehicle within the time period");
                        else if (rentalInDatabase.getRentalCollectionDate().toLocalDate().isEqual(LocalDate.parse(extendDropDate))) {
                            if (rentalInDatabase.getRentalCollectionTime().isBefore(LocalTime.parse(extendDropTime)))
                                throw new Exception("The booking cannot be extended due to another booking created for the vehicle within the time period");
                        }
                    }
                    //check equipments availability
                    if (rental.getEquipment().size() > 0) {
                        for (Equipment equipment : rental.getEquipment()) {
                            if (rentalInDatabase.getEquipment().contains(equipment)) {
                                if (rentalInDatabase.getRentalCollectionDate().toLocalDate().isBefore(LocalDate.parse(extendDropDate)))
                                    throw new Exception("The booking cannot be extended due to another booking created for an equipment within the time period");
                                else if (rentalInDatabase.getRentalCollectionDate().toLocalDate().isEqual(LocalDate.parse(extendDropDate))) {
                                    if (rentalInDatabase.getRentalCollectionTime().isBefore(LocalTime.parse(extendDropTime)))
                                        throw new Exception("The booking cannot be extended due to another booking created for an equipment within the time period");
                                }
                            }
                        }
                    }
                }
            }

            //if extendable
            rental.setRentalReturnDate(Date.valueOf(extendDropDate));
            rental.setRentalReturnTime(LocalTime.parse(extendDropTime));
            rental.setExtended(true);

            rentalRepo.save(rental);
            emailService.updateBooking(rental.getUser().getEmail());
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public String autoBlacklistUser(String name) {
        List<Rental> userRentals = rentalRepo.findAllByStatusAndUserEmail("approved", name);
        User user = userRepo.findUserByEmail(name);

        if (user.isBlacklisted()) {
            return "blacklisted";
        } else if (userRentals.size() > 0) {
            for (Rental rental : userRentals) {
                if (rental.getRentalCollectionDate().toLocalDate().isBefore(LocalDate.now())) {
                    user.setBlacklisted(true);
                    userRepo.save(user);
                    emailService.blacklistUser(user);
                    return "blacklisted";
                }
            }
        } else {
            return "verified";
        }
        return "verified";
    }
}