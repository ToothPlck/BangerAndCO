package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.RentalDto;
import com.example.bangerandco.model.Rental;
import com.example.bangerandco.repository.RentalRepo;
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
        long differenceBetweenHours = ChronoUnit.HOURS.between(startTime, endTime);
        long differenceBetweenMinutes = ChronoUnit.MINUTES.between(startTime, endTime) % 60;

        long total = (differenceBetweenDates * 24) + differenceBetweenHours;
        String periodWithoutMinutes = String.valueOf(total);

        return periodWithoutMinutes + "." + differenceBetweenMinutes;
    }
}