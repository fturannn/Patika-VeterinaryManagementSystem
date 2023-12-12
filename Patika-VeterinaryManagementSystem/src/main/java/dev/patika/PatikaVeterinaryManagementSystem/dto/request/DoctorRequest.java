package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequest {
    private Long id;
    @NotNull(message = "Doktorun ismi boş veya null olamaz")
    private String name;
    @NotNull(message = "Doktorun telefonu boş veya null olamaz")
    private String phone;
    @NotNull(message = "Doktorun maili boş veya null olamaz")
    private String mail;
    @NotNull(message = "Doktorun adresi boş veya null olamaz")
    private String address;
    @NotNull(message = "Doktorun şehri boş veya null olamaz")
    private String city;
}
