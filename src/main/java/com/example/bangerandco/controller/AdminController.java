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

import java.util.List;

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

    @GetMapping("users/view/active")
    public String viewUsers(Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("users", userService.getActiveUsers());
        model.addAttribute("type", "Active Users");
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_view_users";
    }

    @GetMapping("users/view/pending")
    public String viewUnverifiedUsers(Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("users", userService.getUnverifiedUsers());
        model.addAttribute("type", "Pending Users");
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_view_users";
    }

    @GetMapping("users/view/blacklisted")
    public String viewBlacklistedUsers(Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("users", userService.getBlacklistedUsers());
        model.addAttribute("type", "Blacklisted Users");
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_view_users";
    }

    @GetMapping("users/verify/{userId}")
    public String verifyUser(@PathVariable(value = "userId") long userId, Model model, Authentication authentication) {
        try {
            userService.verifyUser(userId);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("users", userService.getUnverifiedUsers());
            model.addAttribute("type", "Pending Users");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_view_users";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("users", userService.getUnverifiedUsers());
        model.addAttribute("type", "Pending Users");
        model.addAttribute("error", "");
        model.addAttribute("success", "User Verified successfully");
        return "admin_view_users";
    }

    @GetMapping("users/blacklist/{userId}")
    public String blacklistUser(@PathVariable(value = "userId") long userId, Model model, Authentication authentication) {
        try {
            userService.blacklistUser(userId);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("users", userService.getActiveUsers());
            model.addAttribute("type", "Active Users");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_view_users";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("users", userService.getActiveUsers());
        model.addAttribute("type", "Active Users");
        model.addAttribute("error", "");
        model.addAttribute("success", "User Blacklisted successfully");
        return "admin_view_users";
    }

    @GetMapping("users/whitelist/{userId}")
    public String whitelistUser(@PathVariable(value = "userId") long userId, Model model, Authentication authentication) {
        try {
            userService.whitelistUser(userId);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("users", userService.getBlacklistedUsers());
            model.addAttribute("type", "Blacklisted Users");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_view_users";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("users", userService.getBlacklistedUsers());
        model.addAttribute("type", "Blacklisted Users");
        model.addAttribute("error", "");
        model.addAttribute("success", "User Whitelisted successfully");
        return "admin_view_users";
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

    @GetMapping("equipment/view/all")
    public String viewAllEquipments(Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("equipments", equipmentService.getAll());
        model.addAttribute("type", "All Equipments");
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_view_equipments";
    }

    @GetMapping("equipment/view/{type}")
    public String viewEquipmentsByType(@PathVariable(value = "type") String type, Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("equipments", equipmentService.getByType(type));
        model.addAttribute("type", type);
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_view_equipments";
    }

    @GetMapping("equipment/update/{equipmentId}")
    public String updateEquipment(@PathVariable("equipmentId") long equipmentId, Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("equipment", equipmentService.updatable(equipmentId));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_update_equipment";
    }

    @PostMapping("equipment/update/{equipmentId}")
    public String updateEquipment(@PathVariable("equipmentId") long equipmentId, @RequestParam("equipmentImage") MultipartFile equipmentImage, EquipmentDto equipmentDto, Model model, Authentication authentication) {
        try {
            equipmentService.updateEquipment(equipmentId, equipmentImage, equipmentDto);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("equipments", equipmentService.getAll());
            model.addAttribute("type", "All Equipments");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_view_equipments";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("equipments", equipmentService.getAll());
        model.addAttribute("type", "All Equipments");
        model.addAttribute("error", "");
        model.addAttribute("success", "Equipment updated successfully");
        return "admin_view_equipments";
    }

    @PostMapping("equipment/delete/{equipmentId}")
    public String deleteEquipment(@PathVariable(value = "equipmentId") long equipmentId, Model model, Authentication authentication) {
        try {
            equipmentService.deleteEquipment(equipmentId);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("equipments", equipmentService.getAll());
            model.addAttribute("type", "All Equipments");
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_view_equipments";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("equipments", equipmentService.getAll());
        model.addAttribute("type", "All Equipments");
        model.addAttribute("error", "");
        model.addAttribute("success", "Equipment deleted successfully");
        return "admin_view_equipments";
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

    @GetMapping("vehicleType/view/all")
    public String viewVehicleTypes(Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_view_vehicle_type";
    }

    @GetMapping("vehicleType/update/{vehicleTypeId}")
    public String updateVehicleType(@PathVariable("vehicleTypeId") long vehicleTypeId, Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
        model.addAttribute("vehicleType", vehicleTypeService.updatable(vehicleTypeId));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_update_vehicleType";
    }

    @PostMapping("vehicleType/update/{vehicleTypeId}")
    public String updateVehicleType(@PathVariable("vehicleTypeId") long vehicleTypeId, @RequestParam("vehicleTypeImage") MultipartFile vehicleTypeImage, VehicleTypeDto vehicleTypeDto, Model model, Authentication authentication) {
        try {
            vehicleTypeService.updateVehicleType(vehicleTypeId, vehicleTypeImage, vehicleTypeDto);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_view_vehicle_type";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
        model.addAttribute("error", "");
        model.addAttribute("success", "Vehicle Type updated successfully");
        return "admin_view_vehicle_type";
    }

    @PostMapping("vehicleType/delete/{vehicleTypeId}")
    public String deleteVehicleType(@PathVariable(value = "vehicleTypeId") long vehicleTypeId, Model model, Authentication authentication) {
        try {
            vehicleTypeService.deleteVehicleType(vehicleTypeId);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_view_vehicle_type";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
        model.addAttribute("error", "");
        model.addAttribute("success", "Vehicle Type deleted successfully");
        return "admin_view_vehicle_type";
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

    @GetMapping("vehicle/view/all")
    public String viewAllVehicles(Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehiclesNav", vehicleService.category_all());
        model.addAttribute("vehicles", vehicleService.category_all());
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_view_vehicles";
    }

    @GetMapping("vehicle/view/{type}")
    public String viewVehiclesByType(@PathVariable(value = "type") long typeId, Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehiclesNav", vehicleService.category_all());
        model.addAttribute("vehicles", vehicleService.category_type(typeId));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_view_vehicles";
    }

    @GetMapping("vehicle/update/{vehicleId}")
    public String updateVehicle(@PathVariable("vehicleId") long vehicleId, Model model, Authentication authentication) {
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehiclesNav", vehicleService.category_all());
        model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
        model.addAttribute("vehicles", vehicleService.updatable(vehicleId));
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "admin_update_vehicle";
    }

    @PostMapping("vehicle/update/{vehicleId}")
    public String updateVehicle(@PathVariable("vehicleId") long vehicleId, @RequestParam("vehicleImage") MultipartFile vehicleImage, VehicleDto vehicleDto, Model model, Authentication authentication) {
        try {
            vehicleService.updateVehicle(vehicleId, vehicleImage, vehicleDto);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("vehiclesNav", vehicleService.category_all());
            model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
            model.addAttribute("vehicles", vehicleService.updatable(vehicleId));
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_update_vehicle";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehiclesNav", vehicleService.category_all());
        model.addAttribute("vehicles", vehicleService.category_all());
        model.addAttribute("error", "");
        model.addAttribute("success", "Vehicle updated successfully");
        return "admin_view_vehicles";
    }

    @PostMapping("vehicle/delete/{vehicleId}")
    public String deleteVehicle(@PathVariable(value = "vehicleId") long vehicleId, Model model, Authentication authentication) {
        try {
            vehicleService.deleteVehicle(vehicleId);
        } catch (Exception exception) {
            model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
            model.addAttribute("vehicles", vehicleService.category_all());
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "admin_view_vehicles";
        }
        model.addAttribute("loggedUser", userService.getUserName(authentication.getName()));
        model.addAttribute("vehicles", vehicleService.category_all());
        model.addAttribute("error", "");
        model.addAttribute("success", "Vehicle deleted successfully");
        return "admin_view_vehicles";
    }

    @GetMapping("/view")
    public String view() {
        return "test";
    }
}