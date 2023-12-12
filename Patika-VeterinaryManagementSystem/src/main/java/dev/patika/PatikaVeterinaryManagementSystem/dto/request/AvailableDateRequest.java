package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateRequest {
    private Long id;
    @NotNull(message = "Müsait günler alanı boş veya null olamaz")
    private LocalDate availableDate;
    @NotNull(message = "Doktor ID'si boş veya null olamaz")
    private Doctor doctor;
}
