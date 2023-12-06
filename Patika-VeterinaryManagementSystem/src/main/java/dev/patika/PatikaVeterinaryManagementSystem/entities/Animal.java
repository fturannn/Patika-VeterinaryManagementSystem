package dev.patika.PatikaVeterinaryManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "animal_name", nullable = false)
    private String name;

    @Column(name = "animal_species", nullable = false)
    private String species;

    @Column(name = "animal_breed", nullable = false)
    private String breed;

    @Column(name = "animal_gender", nullable = false)
    private String gender;

    @Column(name = "animal_colour")
    private String colour;

    @Temporal(TemporalType.DATE)
    @Past
    @Column(name = "animal_birth_date")
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private List<Vaccine> vaccineList;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private List<Appointment> appointmentList;
}
