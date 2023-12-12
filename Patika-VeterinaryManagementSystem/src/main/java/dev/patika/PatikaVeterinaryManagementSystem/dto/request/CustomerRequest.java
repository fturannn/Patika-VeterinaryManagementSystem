package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private Long id;
    @NotNull(message = "Müşteri ismi boş veya null olamaz")
    private String name;
    @NotNull(message = "Müşteri telefonu boş veya null olamaz")
    private String phone;
    @NotNull(message = "Müşteri maili boş veya null olamaz")
    private String mail;
    @NotNull(message = "Müşteri adresi boş veya null olamaz")
    private String address;
    @NotNull(message = "Müşteri şehri boş veya null olamaz")
    private String city;
}
