package com.example.bangerandco.controller;

import com.example.bangerandco.dto.EquipmentDto;
import com.example.bangerandco.dto.UserDto;
import com.example.bangerandco.dto.VehicleDto;
import com.example.bangerandco.dto.VehicleTypeDto;
import com.example.bangerandco.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/")
public class AdminController {

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

    //register
    @GetMapping("register")
    public String register(Model model,
                           Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_register_user";
    }

    @PostMapping("register")
    public String register(UserDto userDto,
                           Model model,
                           Authentication authentication,
                           @RequestParam("userImage") MultipartFile userImage,
                           @RequestParam("licenseImage") MultipartFile licenseImage,
                           @RequestParam("alternateImage") MultipartFile alternateImage) throws Exception {
        try {
            userService.registerUser(userDto, userImage, licenseImage, alternateImage, true);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("userForm", new UserDto());
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_register_user";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("error", "");
        model.addAttribute("success", "User Registered Successfully!");
        return "admin_register_user";
    }

    @GetMapping("equipment/add")
    public String addEquipment(Model model,
                               Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("equipmentForm", new EquipmentDto());
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_add_equipment";
    }

    @PostMapping("equipment/add")
    public String addEquipment(EquipmentDto equipmentDto,
                               Model model,
                               Authentication authentication,
                               @RequestParam("equipmentImage") MultipartFile equipmentImage) throws Exception {
        try {
            equipmentService.save(equipmentDto, equipmentImage);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("equipmentForm", new EquipmentDto());
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_add_equipment";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("equipmentForm", new EquipmentDto());
        model.addAttribute("error", "");
        model.addAttribute("success", "Equipment added successfully!");
        return "admin_add_equipment";
    }

    @GetMapping("vehicleType/add")
    public String addVehicleType(Model model, Authentication authentication) {
        model.addAttribute("vehicleTypeForm", new VehicleTypeDto());
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_add_vehicle_type";
    }

    @PostMapping("vehicleType/add")
    public String addVehicleType(Model model, Authentication authentication, VehicleTypeDto vehicleTypeDto, @RequestParam("vehicleTypeImage") MultipartFile vehicleTypeImage) throws Exception {
        try {
            vehicleTypeService.save(vehicleTypeDto, vehicleTypeImage);
        } catch (Exception exception) {
            model.addAttribute("vehicleTypeForm", new VehicleTypeDto());
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_add_vehicle_type";
        }
        model.addAttribute("vehicleTypeForm", new VehicleTypeDto());
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("error", "");
        model.addAttribute("success", "Vehicle type added successfully!");
        return "admin_add_vehicle_type";
    }

    @GetMapping("vehicle/add")
    public String addVehicle(Model model, Authentication authentication) {
        model.addAttribute("vehicleForm", new VehicleDto());
        model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_add_vehicle";
    }

    @PostMapping("vehicle/add")
    public String addVehicle(Model model, Authentication authentication, VehicleDto vehicleDto, @RequestParam("vehicleImage") MultipartFile vehicleImage) throws Exception {
        try {
            vehicleService.save(vehicleDto, vehicleImage);
        } catch (Exception exception) {
            model.addAttribute("vehicleForm", new VehicleDto());
            model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_add_vehicle";
        }
        model.addAttribute("vehicleForm", new VehicleDto());
        model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("error", "");
        model.addAttribute("success", "Vehicle added successfully!");
        return "admin_add_vehicle";
    }

    @GetMapping("/view")
    public String view(){
        return "test";
    }
}