package dev.patika.PatikaVeterinaryManagementSystem.mapper;

import dev.patika.PatikaVeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface VaccineMapper {

    Vaccine asEntity(VaccineRequest request);

    VaccineResponse asOutput(Vaccine vaccine);

    List<VaccineResponse> asOutput(List<Vaccine> vaccine);

    void update(@MappingTarget Vaccine entity, VaccineRequest request);
}
