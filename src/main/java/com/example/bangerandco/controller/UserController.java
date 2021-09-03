package com.example.bangerandco.controller;

import com.example.bangerandco.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
