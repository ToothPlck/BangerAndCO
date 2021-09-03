package com.example.bangerandco.repository;

import com.example.bangerandco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    User findByDriversLicenseNumber(String driversLicenseNumber);
}
