package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.UserDto;
import com.example.bangerandco.model.User;
import com.example.bangerandco.repository.UserRepo;
import com.example.bangerandco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private String currentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    @Override
    public UserDto getUserName(String email) {
        UserDto userDto = new UserDto();
        User user = userRepo.findUserByEmail(email);

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;
    }

    @Override
    public void registerUser(UserDto userDto, MultipartFile userImage, MultipartFile licenseImage, MultipartFile alternateImage, boolean verification) throws Exception {
        if (userRepo.findUserByEmail(userDto.getEmail()) != null) {
            throw new Exception("The user with the email: " + userDto.getEmail() + " is already registered with BangerAndCo! Please try logging in!");
        } else if (userRepo.findByDriversLicenseNumber(userDto.getDriversLicenseNumber()) != null) {
            throw new Exception("The user with the license number: " + userDto.getDriversLicenseNumber() + " is already registered with BangerAndCo! Please try logging in!");
        } else {
            try {
                User user = new User();

                user.setUserId(userDto.getUserId());
                user.setContact(userDto.getContact());
                user.setCreatedDate(java.sql.Date.valueOf(currentDate()));
                user.setDateOfBirth(java.sql.Date.valueOf(userDto.getDateOfBirth()));
                user.setDriversLicenseNumber(userDto.getDriversLicenseNumber());
                user.setEmail(userDto.getEmail());
                user.setFirstName(userDto.getFirstName());
                user.setBlacklisted(false);
                user.setReturningCustomer(false);
                user.setVerified(verification);
                user.setLastName(userDto.getLastName());
                user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
                user.setRole("user");
                user.setUpdatedDate(java.sql.Date.valueOf(currentDate()));

                User registeredUser = userRepo.save(user);

                try {
                    String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
                    String userImageNameFormat = "user" + registeredUser.getUserId() + ".jpg";
                    String licenseImageNameFormat = "lic" + registeredUser.getUserId() + ".jpg";
                    String alternateImageNameFormat = "alt" + registeredUser.getUserId() + ".jpg";

                    if (userImage.isEmpty()) {
                        throw new Exception("Please insert an image for the profile and try again!");
                    } else if (licenseImage.isEmpty()) {
                        throw new Exception("Please insert an image of the drivers license and try again!");
                    } else if (alternateImage.isEmpty()) {
                        throw new Exception("Please insert an image of a recent utility bill and try again!");
                    } else {
                        byte[] userBytes = userImage.getBytes();
                        Path userPath = Paths.get(imagesFolder + userImageNameFormat);
                        Files.write(userPath, userBytes);

                        byte[] licBytes = licenseImage.getBytes();
                        Path licPath = Paths.get(imagesFolder + licenseImageNameFormat);
                        Files.write(licPath, licBytes);

                        byte[] altBytes = alternateImage.getBytes();
                        Path altPath = Paths.get(imagesFolder + alternateImageNameFormat);
                        Files.write(altPath, altBytes);
                    }

                    user.setUserImagePath(userImageNameFormat);
                    user.setLicenseImagePath(licenseImageNameFormat);
                    user.setAlternateImagePath(alternateImageNameFormat);

                    userRepo.save(user);
                } catch (Exception exception) {
                    throw new Exception("An error occurred while saving the provided images!\n" +
                            "Error : " + exception.getMessage() + " " +
                            " The user can login with the provided information.\n" +
                            " Please login to your account and insert the necessary images!");
                }
            } catch (Exception exception) {
                throw new Exception(exception.getMessage());
            }
        }
    }

//    @Override
//    public UserDto userInfo(String name) {
//        UserDto userDto = new UserDto();
//
//        User user = userRepo.findUserByEmail(name);
//
//        userDto.setUserId(user.getUserId());
//        userDto.setEmail(user.getEmail());
//        userDto.setUserImagePath(user.getUserImagePath());
//
//        return userDto;
//    }
}
