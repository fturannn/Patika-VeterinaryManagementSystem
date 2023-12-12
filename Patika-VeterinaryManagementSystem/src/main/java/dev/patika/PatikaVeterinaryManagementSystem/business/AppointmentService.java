package dev.patika.PatikaVeterinaryManagementSystem.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.PatikaVeterinaryManagementSystem.core.exception.DoctorAppointmentException;
import dev.patika.PatikaVeterinaryManagementSystem.core.exception.HourTypeException;
import dev.patika.PatikaVeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultHelper;
import dev.patika.PatikaVeterinaryManagementSystem.core.utilies.Message;
import dev.patika.PatikaVeterinaryManagementSystem.dao.AppointmentRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AppointmentRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AppointmentResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Appointment;
import dev.patika.PatikaVeterinaryManagementSystem.entities.AvailableDate;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Doctor;
import dev.patika.PatikaVeterinaryManagementSystem.mapper.AppointmentMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public ResultData<AppointmentResponse> getById(Long id) {
        return ResultHelper.success(this.appointmentMapper.asOutput(this.appointmentRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND))));
    }

    public ResultData<List<AppointmentResponse>> findAll() {
        return ResultHelper.success(this.appointmentMapper.asOutput(this.appointmentRepository.findAll()));
    }

    // Kriter 22: Randevu oluşturulurken, doktorun o saatte başka bir randevusu var mı, doktorun müsait günü var mı  kontrolü yapılır
    // Sadece randevusu yoksa ve müsait günü varsa randevu kaydına izin verilir
    public ResultData<AppointmentResponse> save(AppointmentRequest request) {
        Optional<Doctor> isDoctorExist = this.appointmentRepository.findDoctorByDoctorId(request.getDoctor().getId());
        Optional<Animal> isAnimalExist = this.appointmentRepository.findAnimalByAnimalId(request.getAnimal().getId());
        if(isDoctorExist.isEmpty() || isAnimalExist.isEmpty()) {
            throw new NotFoundException("Doktor ya da Hayvan ID'si uyumsuz");
        } else {
            Optional<Appointment> isAppointmentExist = this.appointmentRepository.findByAppointmentDateAndDoctorId(request.getAppointmentDate(), request.getDoctor().getId());
            List<AvailableDate> availableDates = this.appointmentRepository.findAvailableDateByDoctorId(request.getDoctor().getId());

            for(AvailableDate obj : availableDates) {
                if(Objects.equals(obj.getAvailableDate(), request.getAppointmentDate().toLocalDate()) && isAppointmentExist.isEmpty()) {
                    return ResultHelper.created(this.appointmentMapper.asOutput(this.appointmentRepository.save(this.appointmentMapper.asEntity(request))));
                }
            }

            throw new DoctorAppointmentException(request.getDoctor().getId() + " ID'li doktor belirlediğiniz tarih ve saatte müsait değildir.");
        }
    }

    public void delete(Long id) {
        this.appointmentRepository.delete(this.appointmentRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND)));
    }

    public ResultData<AppointmentResponse> update(Long id, AppointmentRequest request) {
        Optional<Doctor> isDoctorExist = this.appointmentRepository.findDoctorByDoctorId(request.getDoctor().getId());
        Optional<Animal> isAnimalExist = this.appointmentRepository.findAnimalByAnimalId(request.getAnimal().getId());
        if(isDoctorExist.isEmpty() || isAnimalExist.isEmpty()) {
            throw new NotFoundException("Doktor ya da Hayvan ID'si uyumsuz");
        } else {
            Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Güncellemeye çalıştığınız randevu sistemde bulunamadı."));
            this.appointmentMapper.update(appointment, request);
            return ResultHelper.success(this.appointmentMapper.asOutput(this.appointmentRepository.save(appointment)));
        }
    }

    public ResultData<List<AppointmentResponse>> appointmentListByDoctorAndDateRange(Long doctorId, LocalDate appointmentDateStart, LocalDate appointmentDateEnd) {
        LocalDateTime appointmentStart = appointmentDateStart.atStartOfDay();
        LocalDateTime appointmentEnd = appointmentDateEnd.atStartOfDay();
        List<Appointment> appointmentList= this.appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, appointmentStart, appointmentEnd);

        if(appointmentList.isEmpty()) {
            throw new DoctorAppointmentException(doctorId + " ID'li doktor için girilen zaman aralıklarında randevu yoktur.");
        }
        return ResultHelper.success(this.appointmentMapper.asOutput(appointmentList));
    }

    public ResultData<List<AppointmentResponse>> appointmentListByAnimalAndDateRange(Long animalId, LocalDate appointmentDateStart, LocalDate appointmentDateEnd) {
        LocalDateTime appointmentStart = appointmentDateStart.atStartOfDay();
        LocalDateTime appointmentEnd = appointmentDateEnd.atStartOfDay();
        List<Appointment> appointmentList= this.appointmentRepository.findByAnimalIdAndAppointmentDateBetween(animalId, appointmentStart, appointmentEnd);

        if(appointmentList.isEmpty()) {
            throw new DoctorAppointmentException(animalId + " ID'li hayvan için girilen zaman aralıklarında randevu yoktur.");
        }
        return ResultHelper.success(this.appointmentMapper.asOutput(appointmentList));
    }
}
