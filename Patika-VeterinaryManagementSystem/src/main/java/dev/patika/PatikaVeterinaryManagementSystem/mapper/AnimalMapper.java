package dev.patika.PatikaVeterinaryManagementSystem.mapper;

import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AnimalMapper {
    Animal asEntity(AnimalRequest request);

    AnimalResponse asOutput(Animal animal);

    List<AnimalResponse> asOutput(List<Animal> animal);

    void update(@MappingTarget Animal entity, AnimalRequest request);
}
