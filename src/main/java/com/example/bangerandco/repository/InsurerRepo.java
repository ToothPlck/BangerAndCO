package com.example.bangerandco.repository;

import com.example.bangerandco.model.Insurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurerRepo extends JpaRepository<Insurer, String> {

}
