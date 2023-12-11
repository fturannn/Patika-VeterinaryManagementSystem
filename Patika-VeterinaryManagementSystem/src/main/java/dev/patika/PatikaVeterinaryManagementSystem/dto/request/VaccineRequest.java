package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineRequest {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Animal animal;
}
