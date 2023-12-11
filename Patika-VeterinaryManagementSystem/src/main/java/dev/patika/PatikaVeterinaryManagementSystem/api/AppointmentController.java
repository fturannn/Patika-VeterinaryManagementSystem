package dev.patika.PatikaVeterinaryManagementSystem.api;

import dev.patika.PatikaVeterinaryManagementSystem.business.AppointmentService;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AppointmentRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AppointmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse getById(@PathVariable("id") Long id) {
        return this.appointmentService.getById(id);
    }

    @PostMapping("/date={date}&hour={hour}")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse save(@PathVariable("date") LocalDate date, @PathVariable("hour") String hour, @RequestBody AppointmentRequest request) {
        return this.appointmentService.save(date, hour, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse update(@PathVariable("id") Long id, @RequestBody AppointmentRequest request) {
        return this.appointmentService.update(id, request);
    }

    @GetMapping("/doctor={doctor}&startDate={appointmentDateStart}&endDate={appointmentDateEnd}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> appointmentListByDoctorAndDateRange (@PathVariable("doctor") String doctorName,
                                                                          @PathVariable("appointmentDateStart") LocalDate appointmentDateStart,
                                                                          @PathVariable("appointmentDateEnd") LocalDate appointmentDateEnd) {
        return this.appointmentService.appointmentListByDoctorAndDateRange(doctorName, appointmentDateStart, appointmentDateEnd);
    }

    @GetMapping("/animal={animal}&startDate={appointmentDateStart}&endDate={appointmentDateEnd}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> appointmentListByAnimalAndDateRange (@PathVariable("animal") String animalName,
                                                                          @PathVariable("appointmentDateStart") LocalDate appointmentDateStart,
                                                                          @PathVariable("appointmentDateEnd") LocalDate appointmentDateEnd) {
        return this.appointmentService.appointmentListByAnimalAndDateRange(animalName, appointmentDateStart, appointmentDateEnd);
    }
}
