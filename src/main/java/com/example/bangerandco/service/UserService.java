package com.example.bangerandco.service;

import com.example.bangerandco.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
//    void registerUser(UserDto userDto, MultipartFile imageFile) throws Exception;

//    UserDto userInfo(String name);

    UserDto getUserName(String email);

    void registerUser(UserDto userDto, MultipartFile userImage, MultipartFile licenseImage, MultipartFile alternateImage, boolean verified) throws Exception;
}
