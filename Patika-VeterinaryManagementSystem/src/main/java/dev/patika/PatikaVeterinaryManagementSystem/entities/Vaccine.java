package dev.patika.PatikaVeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "Vaccines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private Long id;

    @Column(name = "vaccine_name", nullable = false)
    private String name;

    @Column(name = "vaccine_code", nullable = false)
    private String code;

    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    @Column(name = "vaccine_protection_start_date", nullable = false)
    private LocalDate protectionStartDate;

    @Temporal(TemporalType.DATE)
    @Future
    @Column(name = "vaccine_protection_finish_date", nullable = false)
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_animal_id", referencedColumnName = "animal_id")
    private Animal animal;
}
