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

@Controller
@RequestMapping("/")
public class GuestController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("error", "");
        model.addAttribute("success", "");
        return "login";
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
}
