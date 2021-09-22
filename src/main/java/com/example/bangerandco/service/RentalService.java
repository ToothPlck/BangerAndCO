package com.example.bangerandco.service;

import com.example.bangerandco.dto.RentalDto;

import java.util.List;

public interface RentalService {

    List<RentalDto> userOnGoingRentals(String name);

    String periodInHours(String pickDate, String pickTime, String dropDate, String dropTime);

    void createBooking(String name, String vehicleId, List<String> equipments, String hours, String pickDate, String pickTime, String dropDate, String dropTime) throws Exception;
}
