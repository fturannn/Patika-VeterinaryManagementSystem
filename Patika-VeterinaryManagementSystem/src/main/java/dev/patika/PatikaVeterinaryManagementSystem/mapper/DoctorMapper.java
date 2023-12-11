package dev.patika.PatikaVeterinaryManagementSystem.mapper;

import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.DoctorRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.DoctorResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface DoctorMapper {
    Doctor asEntity(DoctorRequest request);

    DoctorResponse asOutput(Doctor doctor);

    List<DoctorResponse> asOutput(List<Doctor> doctor);

    void update(@MappingTarget Doctor entity, DoctorRequest request);
}
