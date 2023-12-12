package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequest {
    private Long id;
    @NotNull(message = "Hayvan ismi boş veya null olamaz")
    private String name;
    @NotNull(message = "Hayvan türü boş veya null olamaz")
    private String species;
    @NotNull(message = "Hayvan cinsi boş veya null olamaz")
    private String breed;
    @NotNull(message = "Hayvan cinsiyeti boş veya null olamaz")
    private String gender;
    @NotNull(message = "Hayvan rengi boş veya null olamaz")
    private String colour;
    @NotNull(message = "Hayvan doğum tarihi boş veya null olamaz")
    private LocalDate dateOfBirth;
    @NotNull(message = "Müşteri ID'si boş bırakılamaz")
    private Customer customer;
}
