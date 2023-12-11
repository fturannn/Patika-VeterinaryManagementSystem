package dev.patika.PatikaVeterinaryManagementSystem.dao;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAppointmentDateAndDoctorId(LocalDateTime AppointmentDate, Long doctorId);

    List<Appointment> findByDoctorNameAndAppointmentDateBetween(String doctorName, LocalDateTime AppointmentDateStart, LocalDateTime AppointmentDateEnd);

    List<Appointment> findByAnimalNameAndAppointmentDateBetween(String animalName, LocalDateTime AppointmentDateStart, LocalDateTime AppointmentDateEnd);
}
