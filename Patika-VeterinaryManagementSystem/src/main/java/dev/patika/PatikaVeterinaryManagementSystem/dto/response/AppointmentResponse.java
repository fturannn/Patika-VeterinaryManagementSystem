package dev.patika.PatikaVeterinaryManagementSystem.dto.response;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;
}
