package dev.patika.PatikaVeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "appointment_date")
    @FutureOrPresent
    private LocalDateTime appointmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_animal_id", referencedColumnName = "animal_id")
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
}