package com.example.bangerandco.service;

import com.example.bangerandco.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
//    void registerUser(UserDto userDto, MultipartFile imageFile) throws Exception;

//    UserDto userInfo(String name);

    UserDto getUserName(String email);

    void registerUser(UserDto userDto, MultipartFile userImage, MultipartFile licenseImage, MultipartFile alternateImage, boolean verified) throws Exception;

    List<UserDto> getActiveUsers();

    List<UserDto> getUnverifiedUsers();

    List<UserDto> getBlacklistedUsers();

    UserDto findById(Long userId);

    void verifyUser(long userId) throws Exception;

    void blacklistUser(long userId) throws Exception;

    void whitelistUser(long userId) throws Exception;
}
