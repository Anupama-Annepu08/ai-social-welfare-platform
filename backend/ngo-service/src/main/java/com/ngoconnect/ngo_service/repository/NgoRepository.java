package com.ngoconnect.ngo_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngoconnect.ngo_service.entity.Ngo;

public interface NgoRepository extends JpaRepository<Ngo, Long> {

    Optional<Ngo> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByRegistrationNumber(String registrationNumber);
}