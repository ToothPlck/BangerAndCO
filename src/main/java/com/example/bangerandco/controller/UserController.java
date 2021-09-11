package com.example.bangerandco.controller;

import com.example.bangerandco.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("search/available")
    public String searchAvailable(Model model,
                                  Authentication authentication,
                                  @RequestParam("pickDate") String pickDate,
                                  @RequestParam("pickTime") String pickTime,
                                  @RequestParam("dropDate") String dropDate,
                                  @RequestParam("dropTime") String dropTime) {
        model.addAttribute("vehicles", vehicleService.available(pickDate, pickTime, dropDate, dropTime));
        model.addAttribute("equipments", equipmentService.available(pickDate, pickTime, dropDate, dropTime));
        model.addAttribute("loggedUser", userService.getUserDetails(authentication.getName()));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "user_view_available";
    }
}
