package com.example.bangerandco.controller;

import com.example.bangerandco.dto.UserDto;
import com.example.bangerandco.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class GuestController {

    @Autowired
    private UserService userService;
    @Autowired
    private VehicleTypeService vehicleTypeService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private RentalService rentalService;

    @GetMapping("login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "The username and password did not match! Please try again.");
        }
        return "login";
    }

    @GetMapping("direct")
    public String successLogin(Model model, Authentication authentication) {
        UserDto user = userService.getUserRole(authentication.getName());


        if (user.getRole().equals("admin")) {
            return "redirect:/admin/competitors";
        } else {
            String validUser = rentalService.autoBlacklistUser(authentication.getName());
            if (validUser.equals("verified")) {
                return "redirect:/user/home";
            } else {
                model.addAttribute("error", "black");
                return "login";
            }
        }
    }

    //register
    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userForm", new UserDto());
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "guest_register";
    }

    @PostMapping("register")
    public String register(UserDto userDto,
                           Model model,
                           @RequestParam("userImage") MultipartFile userImage,
                           @RequestParam("licenseImage") MultipartFile licenseImage,
                           @RequestParam("alternateImage") MultipartFile alternateImage) throws Exception {
        try {
            userService.registerUser(userDto, userImage, licenseImage, alternateImage, false);
        } catch (Exception exception) {
            model.addAttribute("userForm", new UserDto());
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("success", "");
            return "guest_register";
        }
        model.addAttribute("error", "");
        model.addAttribute("success", "Registration Successful!");
        return "login";
    }

    @GetMapping("category")
    public String category(Model model) {
        model.addAttribute("categories", vehicleTypeService.getAll());
        return "guest_category";
    }

    @GetMapping("category/all")
    public String categoryAll(Model model) {
        model.addAttribute("categories", vehicleService.category_all());
        return "guest_category_vehicles";
    }

    @GetMapping("category/{typeId}")
    public String categoryByType(@PathVariable(value = "typeId") Long typeId, Model model) {
        model.addAttribute("categories", vehicleService.category_type(typeId));
        return "guest_category_vehicles";
    }
}
