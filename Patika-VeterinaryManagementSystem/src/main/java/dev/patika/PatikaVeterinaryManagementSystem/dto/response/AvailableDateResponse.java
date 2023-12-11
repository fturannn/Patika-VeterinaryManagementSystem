package dev.patika.PatikaVeterinaryManagementSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
}
