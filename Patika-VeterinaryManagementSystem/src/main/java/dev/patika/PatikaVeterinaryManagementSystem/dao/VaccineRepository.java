package dev.patika.PatikaVeterinaryManagementSystem.dao;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByCodeAndAnimalId(String code, Long animalId);

}
