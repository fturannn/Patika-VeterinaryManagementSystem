package dev.patika.PatikaVeterinaryManagementSystem.dao;

import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    @Query("SELECT a.vaccineList FROM Animal a WHERE a.id = :animalId")
    List<Vaccine> findByAnimalId(@Param("animalId") Long animalId);

    @Query("SELECT a FROM Animal a JOIN a.vaccineList v WHERE v.name = :vaccineName AND v.code = :vaccineCode")
    List<Vaccine> findByVaccineNameAndVaccineCode(@Param("vaccineName") String vaccineName, @Param("vaccineCode") String vaccineCode);
}
