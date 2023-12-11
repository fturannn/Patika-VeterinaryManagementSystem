package dev.patika.PatikaVeterinaryManagementSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequest {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
