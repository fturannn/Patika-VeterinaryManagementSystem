package dev.patika.PatikaVeterinaryManagementSystem.dao;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);

    /*
    @Query("SELECT c FROM Customer c JOIN c.animalList a WHERE c.name = :customerName")
    List<Animal> findAnimalsByCustomerName(@Param("customerName") String customerName);

    @Query("SELECT c.animalList FROM Customer c WHERE c.name = :customerName")
    List<Animal> findAnimalsByCustomerName(@Param("customerName") String customerName);
     */
}
