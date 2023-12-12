package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    private Long id;
    @NotNull(message = "Randevu tarihi boş veya null olamaz")
    private LocalDateTime appointmentDate;
    @NotNull(message = "Hayvan ID'si boş veya null olamaz")
    private Animal animal;
    @NotNull(message = "Doktor ID'si boş veya null olamaz")
    private Doctor doctor;
}
