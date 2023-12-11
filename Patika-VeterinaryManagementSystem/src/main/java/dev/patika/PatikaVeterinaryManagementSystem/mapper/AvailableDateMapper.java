package dev.patika.PatikaVeterinaryManagementSystem.mapper;

import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AvailableDateResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AvailableDateMapper {
    AvailableDate asEntity(AvailableDateRequest request);

    AvailableDateResponse asOutput(AvailableDate availableDate);

    List<AvailableDateResponse> asOutput(List<AvailableDate> availableDate);

    void update(@MappingTarget AvailableDate entity, AvailableDateRequest request);
}
