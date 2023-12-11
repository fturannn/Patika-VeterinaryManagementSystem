package dev.patika.PatikaVeterinaryManagementSystem.mapper;

import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AppointmentRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AppointmentResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    Appointment asEntity(AppointmentRequest request);

    AppointmentResponse asOutput(Appointment appointment);

    List<AppointmentResponse> asOutput(List<Appointment> appointment);

    void update(@MappingTarget Appointment entity, AppointmentRequest request);
}
