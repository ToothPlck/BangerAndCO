package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.RentalDto;
import com.example.bangerandco.model.Rental;
import com.example.bangerandco.repository.RentalRepo;
import com.example.bangerandco.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}