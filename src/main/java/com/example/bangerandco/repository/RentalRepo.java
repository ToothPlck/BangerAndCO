package com.example.bangerandco.repository;

import com.example.bangerandco.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {
}
