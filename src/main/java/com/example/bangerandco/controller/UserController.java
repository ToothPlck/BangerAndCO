package com.example.bangerandco.controller;

import com.example.bangerandco.dto.EquipmentDto;
import com.example.bangerandco.dto.RentalDto;
import com.example.bangerandco.dto.UserDto;
import com.example.bangerandco.model.Rental;
import com.example.bangerandco.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private VehicleTypeService vehicleTypeService;
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("home")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("onGoingRentals", rentalService.userOnGoingRentals(authentication.getName()));
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("equipments", equipmentService.getAll());
        model.addAttribute("vehicles", vehicleService.category_all());
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "user_home";
    }

    @GetMapping("update/account")
    public String updateAccount(Model model,
                                Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "user_update_account";
    }

    @PostMapping("update/account")
    public String updateAccount(Model model,
                                Authentication authentication,
                                UserDto userDto,
                                @RequestParam("userImage") MultipartFile userImage,
                                @RequestParam("licenseImage") MultipartFile licenseImage,
                                @RequestParam("alternateImage") MultipartFile alternateImage) {
        try {
            userService.updateAccount(userDto, userImage, licenseImage, alternateImage);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "user_update_account";
        }
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("error", "");
        model.addAttribute("success", "Account Updated");
        return "user_update_account";
    }

    @PostMapping("search/available")
    public String searchAvailable(Model model,
                                  Authentication authentication,
                                  @RequestParam("pickDate") String pickDate,
                                  @RequestParam("pickTime") String pickTime,
                                  @RequestParam("dropDate") String dropDate,
                                  @RequestParam("dropTime") String dropTime) {
        model.addAttribute("hours", rentalService.periodInHours(pickDate, pickTime, dropDate, dropTime));
        model.addAttribute("pickDate", pickDate);
        model.addAttribute("pickTime", pickTime);
        model.addAttribute("dropDate", dropDate);
        model.addAttribute("dropTime", dropTime);
        model.addAttribute("vehicles", vehicleService.available(pickDate, pickTime, dropDate, dropTime, authentication.getName()));
        model.addAttribute("equipments", equipmentService.available(pickDate, pickTime, dropDate, dropTime));
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "user_view_available";
    }

    @PostMapping("set/booking")
    public String setBooking(Model model,
                             Authentication authentication,
                             @RequestParam("vehicle") String vehicleId,
                             @RequestParam("equipments") List<String> equipments,
                             @RequestParam("hours") String hours,
                             @RequestParam("bookingTotal") String total,
                             @RequestParam("setBookingPickupDate") String pickDate,
                             @RequestParam("setBookingPickupTime") String pickTime,
                             @RequestParam("setBookingDropOffDate") String dropDate,
                             @RequestParam("setBookingDropOffTime") String dropTime) {
        try {
            rentalService.createBooking(authentication.getName(), vehicleId, equipments, hours, pickDate, pickTime, dropDate, dropTime, total);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "redirect:/user/home";
    }

    @GetMapping("rentals/all")
    public String viewBookingsAll(Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("bookings", rentalService.userAllRentals(authentication.getName()));
        model.addAttribute("type", "All bookings");
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "user_view_rentals";
    }

    @GetMapping("rentals/{status}")
    public String viewBookingsByStatus(@PathVariable(value = "status") String status, Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("bookings", rentalService.userRentalByStatus(authentication.getName(), status));
        model.addAttribute("type", status);
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "user_view_rentals";
    }

    @GetMapping("rental/cancel/{rentalId}")
    public String cancelBooking(@PathVariable(value = "rentalId") long rentalId, Model model, Authentication authentication) {
        try {
            rentalService.cancelRental(rentalId);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
            model.addAttribute("bookings", rentalService.userAllRentals(authentication.getName()));
            model.addAttribute("type", "All bookings");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "user_view_rentals";
        }
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("bookings", rentalService.userAllRentals(authentication.getName()));
        model.addAttribute("type", "All bookings");
        model.addAttribute("error", "");
        model.addAttribute("success", "Booking cancelled successfully");
        return "user_view_rentals";
    }

    @GetMapping("rental/update/{rentalId}")
    public String updateBooking(@PathVariable(value = "rentalId") long rentalId, Model model, Authentication authentication) {
        try {
            Rental rentalDetails = rentalService.rentalDetails(rentalId);
            List<EquipmentDto> availableEquipments = equipmentService.available(rentalDetails.getRentalCollectionDate().toString(),
                    rentalDetails.getRentalCollectionTime().toString(),
                    rentalDetails.getRentalReturnDate().toString(),
                    rentalDetails.getRentalReturnTime().toString());
            RentalDto rentalDto = rentalService.getRentalById(rentalId);
            model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
            model.addAttribute("equipments", availableEquipments);
            model.addAttribute("rental", rentalDto);
            model.addAttribute("hours", rentalService.periodInHours(rentalDetails.getRentalCollectionDate().toString(),
                    rentalDetails.getRentalCollectionTime().toString(),
                    rentalDetails.getRentalReturnDate().toString(),
                    rentalDetails.getRentalReturnTime().toString()));
            model.addAttribute("error", "");
            model.addAttribute("success", "");
            return "user_update_rental";
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
            model.addAttribute("bookings", rentalService.userAllRentals(authentication.getName()));
            model.addAttribute("type", "All bookings");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "user_view_rentals";
        }
    }

    @PostMapping("rental/update/{rentalId}")
    public String updateBooking(@PathVariable(value = "rentalId") long rentalId,
                                @RequestParam("equipments") List<String> equipments,
                                @RequestParam("bookingTotal") String total,
                                Model model,
                                Authentication authentication) {
        try {
            rentalService.updateBooking(rentalId, equipments, total);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
            model.addAttribute("bookings", rentalService.userAllRentals(authentication.getName()));
            model.addAttribute("type", "All bookings");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "user_view_rentals";
        }
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("bookings", rentalService.userAllRentals(authentication.getName()));
        model.addAttribute("type", "All bookings");
        model.addAttribute("error", "");
        model.addAttribute("success", "Booking updated successfully");
        return "user_view_rentals";
    }

    @PostMapping("rental/extend/{rentalId}")
    public String extendBooking(@PathVariable(value = "rentalId") long rentalId,
                                @RequestParam("extendDropDate") String extendDropDate,
                                @RequestParam("extendDropTime") String extendDropTime,
                                Model model,
                                Authentication authentication) {
        try {
            rentalService.extendBooking(rentalId, extendDropDate, extendDropTime);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
            model.addAttribute("bookings", rentalService.userAllRentals(authentication.getName()));
            model.addAttribute("type", "All bookings");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "user_view_rentals";
        }
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("bookings", rentalService.userAllRentals(authentication.getName()));
        model.addAttribute("type", "All bookings");
        model.addAttribute("error", "");
        model.addAttribute("success", "Booking extended successfully");
        return "user_view_rentals";
    }
}
