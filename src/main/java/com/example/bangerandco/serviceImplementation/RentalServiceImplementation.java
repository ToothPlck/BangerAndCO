package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.RentalDto;
import com.example.bangerandco.model.Rental;
import com.example.bangerandco.repository.RentalRepo;
import com.example.bangerandco.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImplementation implements RentalService {

    @Autowired
    private RentalRepo rentalRepo;

}