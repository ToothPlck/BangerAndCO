package com.example.bangerandco.serviceImplementation;

import com.example.bangerandco.dto.RentalDto;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                user.setBangerScore(10);

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

    @Override
    public List<UserDto> getActiveUsers() {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userRepo.findActiveUsers()) {
            if (user.getRole().equals("user")) {
                UserDto userDto = new UserDto();

                userDto.setUserId(user.getUserId());
                userDto.setUserImagePath(user.getUserImagePath());
                userDto.setContact(user.getContact());
                userDto.setCreatedDate(user.getCreatedDate().toString());
                userDto.setDateOfBirth(user.getDateOfBirth().toString());
                userDto.setDriversLicenseNumber(user.getDriversLicenseNumber());
                userDto.setEmail(user.getEmail());
                userDto.setFirstName(user.getFirstName());
                userDto.setBlacklisted(user.isBlacklisted());
                userDto.setReturningCustomer(user.isReturningCustomer());
                userDto.setVerified(user.isVerified());
                userDto.setLastName(user.getLastName());
                userDto.setLicenseImagePath(user.getLicenseImagePath());
                userDto.setAlternateImagePath(user.getAlternateImagePath());
                userDto.setBangerScore(user.getBangerScore());

                userDtoList.add(userDto);
            }
        }
        return userDtoList;
    }

    @Override
    public List<UserDto> getUnverifiedUsers() {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userRepo.findUnverifiedUsers()) {
            if (user.getRole().equals("user")) {
                UserDto userDto = new UserDto();

                userDto.setUserId(user.getUserId());
                userDto.setUserImagePath(user.getUserImagePath());
                userDto.setContact(user.getContact());
                userDto.setCreatedDate(user.getCreatedDate().toString());
                userDto.setDateOfBirth(user.getDateOfBirth().toString());
                userDto.setDriversLicenseNumber(user.getDriversLicenseNumber());
                userDto.setEmail(user.getEmail());
                userDto.setFirstName(user.getFirstName());
                userDto.setBlacklisted(user.isBlacklisted());
                userDto.setReturningCustomer(user.isReturningCustomer());
                userDto.setVerified(user.isVerified());
                userDto.setLastName(user.getLastName());
                userDto.setLicenseImagePath(user.getLicenseImagePath());
                userDto.setAlternateImagePath(user.getAlternateImagePath());
                userDto.setBangerScore(user.getBangerScore());

                userDtoList.add(userDto);
            }
        }
        return userDtoList;
    }

    @Override
    public List<UserDto> getBlacklistedUsers() {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userRepo.findBlacklistedUsers()) {
            if (user.getRole().equals("user")) {
                UserDto userDto = new UserDto();

                userDto.setUserId(user.getUserId());
                userDto.setUserImagePath(user.getUserImagePath());
                userDto.setContact(user.getContact());
                userDto.setCreatedDate(user.getCreatedDate().toString());
                userDto.setDateOfBirth(user.getDateOfBirth().toString());
                userDto.setDriversLicenseNumber(user.getDriversLicenseNumber());
                userDto.setEmail(user.getEmail());
                userDto.setFirstName(user.getFirstName());
                userDto.setBlacklisted(user.isBlacklisted());
                userDto.setReturningCustomer(user.isReturningCustomer());
                userDto.setVerified(user.isVerified());
                userDto.setLastName(user.getLastName());
                userDto.setLicenseImagePath(user.getLicenseImagePath());
                userDto.setAlternateImagePath(user.getAlternateImagePath());
                userDto.setBangerScore(user.getBangerScore());

                userDtoList.add(userDto);
            }
        }
        return userDtoList;
    }

    @Override
    public UserDto findById(Long userId) {
        UserDto userDto = new UserDto();
        User user = userRepo.getById(userId);

        userDto.setUserId(user.getUserId());
        userDto.setUserImagePath(user.getUserImagePath());
        userDto.setContact(user.getContact());
        userDto.setCreatedDate(user.getCreatedDate().toString());
        userDto.setDateOfBirth(user.getDateOfBirth().toString());
        userDto.setDriversLicenseNumber(user.getDriversLicenseNumber());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setBlacklisted(user.isBlacklisted());
        userDto.setReturningCustomer(user.isReturningCustomer());
        userDto.setVerified(user.isVerified());
        userDto.setLastName(user.getLastName());
        userDto.setLicenseImagePath(user.getLicenseImagePath());
        userDto.setAlternateImagePath(user.getAlternateImagePath());
        userDto.setBangerScore(user.getBangerScore());

        return userDto;
    }

    @Override
    public void verifyUser(long userId) throws Exception {
        try {
            User user = userRepo.getById(userId);
            user.setVerified(true);
            userRepo.save(user);
        } catch (Exception exception) {
            throw new Exception("An exception occurred while verifying the user!");
        }
    }

    @Override
    public void blacklistUser(long userId) throws Exception {
        try {
            User user = userRepo.getById(userId);
            user.setBlacklisted(true);
            userRepo.save(user);
        } catch (Exception exception) {
            throw new Exception("An exception occurred while blacklisting the user");
        }
    }

    @Override
    public void whitelistUser(long userId) throws Exception {
        try {
            User user = userRepo.getById(userId);
            user.setBlacklisted(false);
            userRepo.save(user);
        } catch (Exception exception) {
            throw new Exception("An exception occurred while whitelisting the user");
        }
    }

    @Override
    public UserDto getUserDetails(String email) {
        UserDto userDto = new UserDto();
        User user = userRepo.findUserByEmail(email);

        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBangerScore(user.getBangerScore());
        userDto.setEmail(user.getEmail());
        userDto.setAlternateImagePath(user.getAlternateImagePath());
        userDto.setLicenseImagePath(user.getLicenseImagePath());
        userDto.setUserImagePath(user.getUserImagePath());
        userDto.setContact(user.getContact());
        userDto.setCreatedDate(user.getCreatedDate().toString());
        userDto.setUpdatedDate(user.getUpdatedDate().toString());
        userDto.setDateOfBirth(user.getDateOfBirth().toString());
        userDto.setDriversLicenseNumber(user.getDriversLicenseNumber());
        userDto.setPassword(null);

        return userDto;
    }

    @Override
    public void updateAccount(UserDto userDto, MultipartFile userImage, MultipartFile licenseImage, MultipartFile alternateImage) throws Exception {
        List<User> usersWithEmail = userRepo.findUsersByEmail(userDto.getEmail());
        if (usersWithEmail.size() != 0) {
            for (User userWithEmail : usersWithEmail) {
                if (userDto.getUserId() != userWithEmail.getUserId()) {
                    throw new Exception("The user with the email: " + userDto.getEmail() + " is already registered with BangerAndCo! Please try logging in!");
                }
            }
        }

        User user = userRepo.getById(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setContact(userDto.getContact());
        user.setEmail(userDto.getEmail());

        if (!userDto.getPassword().equals("") && userDto.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }

        if (!userImage.isEmpty()) {
            String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
            String userImageNameFormat = "user" + user.getUserId() + ".jpg";

            Path deletePath = Paths.get(imagesFolder + user.getUserImagePath());
            Files.delete(deletePath);

            byte[] userByte = userImage.getBytes();
            Path userPath = Paths.get(imagesFolder + userImageNameFormat);
            Files.write(userPath, userByte);

            user.setUserImagePath(userImageNameFormat);
        }

        if (!licenseImage.isEmpty()) {
            String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
            String licenseImageNameFormat = "lic" + user.getUserId() + ".jpg";

            Path deletePath = Paths.get(imagesFolder + user.getLicenseImagePath());
            Files.delete(deletePath);

            byte[] licenseByte = licenseImage.getBytes();
            Path licensePath = Paths.get(imagesFolder + licenseImageNameFormat);
            Files.write(licensePath, licenseByte);

            user.setLicenseImagePath(licenseImageNameFormat);
        }

        if (!alternateImage.isEmpty()) {
            String imagesFolder = "D:/APIIT/3rd year/EIRLSS-1/BnC/src/main/webapp/images/";
            String altImageNameFormat = "alt" + user.getUserId() + ".jpg";

            Path deletePath = Paths.get(imagesFolder + user.getAlternateImagePath());
            Files.delete(deletePath);

            byte[] altByte = alternateImage.getBytes();
            Path altPath = Paths.get(imagesFolder + altImageNameFormat);
            Files.write(altPath, altByte);

            user.setAlternateImagePath(altImageNameFormat);
        }

        userRepo.save(user);
    }
}
