package com.example.bangerandco.service;

import com.example.bangerandco.dto.RentalDto;

import java.util.List;

public interface RentalService {

    List<RentalDto> userOnGoingRentals(String name);
}
