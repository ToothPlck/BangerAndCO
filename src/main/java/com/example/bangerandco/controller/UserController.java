package com.example.bangerandco.controller;

import com.example.bangerandco.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
