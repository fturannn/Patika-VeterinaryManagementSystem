package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequest {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Customer customer;
}
