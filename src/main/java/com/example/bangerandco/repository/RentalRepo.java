package com.example.bangerandco.repository;

import com.example.bangerandco.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {

    List<Rental> findAllByUserUserId(Long userId);
}
