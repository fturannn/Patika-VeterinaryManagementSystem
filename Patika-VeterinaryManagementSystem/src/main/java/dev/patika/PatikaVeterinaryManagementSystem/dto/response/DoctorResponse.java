package dev.patika.PatikaVeterinaryManagementSystem.dto.response;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Appointment;
import dev.patika.PatikaVeterinaryManagementSystem.entities.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponse {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<Appointment> appointmentList;
    private List<AvailableDate> availableDateList;
}
