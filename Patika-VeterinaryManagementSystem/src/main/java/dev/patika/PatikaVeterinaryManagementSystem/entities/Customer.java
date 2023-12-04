package dev.patika.PatikaVeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_phone", nullable = false, columnDefinition = "VARCHAR(11)")
    private String phone;

    @Column(name = "customer_mail", nullable = false)
    @Email
    private String mail;

    @Column(name = "customer_address", nullable = false)
    private String address;

    @Column(name = "customer_city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "customer")
    private List<Animal> animalList;
}
