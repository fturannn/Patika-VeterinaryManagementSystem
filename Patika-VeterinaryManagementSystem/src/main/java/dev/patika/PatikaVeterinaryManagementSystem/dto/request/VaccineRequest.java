package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineRequest {
    private Long id;
    @NotNull(message = "Aşı ismi boş veya null olamaz")
    private String name;
    @NotNull(message = "Aşı kodu boş veya null olamaz")
    private String code;
    @NotNull(message = "Aşı koruyuculuk tarihi başlangıcı boş veya null olamaz")
    private LocalDate protectionStartDate;
    @NotNull(message = "Aşı koruyuculuk tarihi bitişi boş veya null olamaz")
    private LocalDate protectionFinishDate;
    @NotNull(message = "Hayvan ID'si boş veya null olamaz")
    private Animal animal;
}
