package dev.patika.PatikaVeterinaryManagementSystem.dao;

import dev.patika.PatikaVeterinaryManagementSystem.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
    Optional<AvailableDate> findByAvailableDate(LocalDate availableDate);
}
