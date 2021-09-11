package com.example.bangerandco.repository;

import com.example.bangerandco.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {

    List<Rental> findAllByUserEmailAndStatus(String email, String status);

    List<Rental> findAllByUserUserId(Long userId);

//    List<Rental> findByRentalCollectionDateAfterAndRentalReturnDateBefore(Date returnCollection, Date returnDate);
}
