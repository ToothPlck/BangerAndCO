package com.example.bangerandco.repository;

import com.example.bangerandco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    User findByDriversLicenseNumber(String driversLicenseNumber);

    @Query("from User u where u.isVerified=true and u.isBlacklisted=false")
    List<User> findActiveUsers();

    @Query("from User u where u.isVerified=false and u.isBlacklisted=false")
    List<User> findUnverifiedUsers();

    @Query("from User u where u.isBlacklisted=true ")
    List<User> findBlacklistedUsers();

    @Query("from User u where u.email=:email")
    List<User> findUsersByEmail(String email);
}
