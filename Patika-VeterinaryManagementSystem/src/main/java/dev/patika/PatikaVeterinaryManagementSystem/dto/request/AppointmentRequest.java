package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    private Long id;
    private Animal animal;
    private Doctor doctor;
}
