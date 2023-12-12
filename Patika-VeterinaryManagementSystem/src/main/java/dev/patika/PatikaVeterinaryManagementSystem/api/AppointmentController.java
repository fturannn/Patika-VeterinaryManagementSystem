package dev.patika.PatikaVeterinaryManagementSystem.api;

import dev.patika.PatikaVeterinaryManagementSystem.business.AppointmentService;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AppointmentRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AppointmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResultData<AppointmentResponse> getById(@PathVariable("id") Long id) {
        return this.appointmentService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public  ResultData<List<AppointmentResponse>> findAll() {
        return this.appointmentService.findAll();
    }

    // Kriter 14: Proje isterlerine göre randevu kaydedilir
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@RequestBody AppointmentRequest request) {
        return this.appointmentService.save(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@PathVariable("id") Long id, @RequestBody AppointmentRequest request) {
        return this.appointmentService.update(id, request);
    }

    // Kriter 24: Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtrelenir
    @GetMapping("/doctorId")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> appointmentListByDoctorAndDateRange (@RequestParam("doctorId") Long doctorId,
                                                                          @RequestParam("appointmentDateStart") LocalDate appointmentDateStart,
                                                                          @RequestParam("appointmentDateEnd") LocalDate appointmentDateEnd) {
        return this.appointmentService.appointmentListByDoctorAndDateRange(doctorId, appointmentDateStart, appointmentDateEnd);
    }

    // Kriter 23: Randevular kullanıcı tarafından girilen tarih aralığına ve hayvana göre filtrelenir
    @GetMapping("/animalId")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> appointmentListByAnimalAndDateRange (@RequestParam("animalId") Long animalId,
                                                                          @RequestParam("appointmentDateStart") LocalDate appointmentDateStart,
                                                                          @RequestParam("appointmentDateEnd") LocalDate appointmentDateEnd) {
        return this.appointmentService.appointmentListByAnimalAndDateRange(animalId, appointmentDateStart, appointmentDateEnd);
    }
}
