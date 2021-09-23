package com.example.bangerandco.service;

import com.example.bangerandco.dto.RentalDto;

import java.util.List;

public interface RentalService {

    List<RentalDto> userOnGoingRentals(String name);

    String periodInHours(String pickDate, String pickTime, String dropDate, String dropTime);

    void createBooking(String name, String vehicleId, List<String> equipments, String hours, String pickDate, String pickTime, String dropDate, String dropTime, String total) throws Exception;

    List<RentalDto> status_all();

    List<RentalDto> status(String status);

    void changeStatus(String status, long rentalId);

    List<RentalDto> userAllRentals(String name);

    List<RentalDto> userRentalByStatus(String name, String status);

    void cancelRental(long rentalId) throws Exception;
}
