package com.example.bangerandco.controller;

import com.example.bangerandco.dto.UserDto;
import com.example.bangerandco.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        model.addAttribute("vehicles", vehicleService.available(pickDate, pickTime, dropDate, dropTime));
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
                             @RequestParam("equipments")List<String> equipments,
                             @RequestParam("hours")String hours,
                             @RequestParam("setBookingPickupDate") String pickDate,
                             @RequestParam("setBookingPickupTime") String pickTime,
                             @RequestParam("setBookingDropOffDate") String dropDate,
                             @RequestParam("setBookingDropOffTime") String dropTime){
        try {
            rentalService.createBooking(authentication.getName(), vehicleId, equipments, hours, pickDate, pickTime, dropDate, dropTime);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return "redirect:/user/home";
    }
}
