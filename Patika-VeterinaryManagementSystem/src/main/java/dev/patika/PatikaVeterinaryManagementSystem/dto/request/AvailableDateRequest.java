package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateRequest {
    private Long id;
    private LocalDate availableDate;
}
