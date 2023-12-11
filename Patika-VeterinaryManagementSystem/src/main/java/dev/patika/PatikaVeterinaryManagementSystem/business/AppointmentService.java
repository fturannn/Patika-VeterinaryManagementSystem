package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.AppointmentRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AppointmentRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AppointmentResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Appointment;
import dev.patika.PatikaVeterinaryManagementSystem.entities.AvailableDate;
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

    public AppointmentResponse getById(Long id) {
        Optional<Appointment> isAppointmentExist = this.appointmentRepository.findById(id);
        if(isAppointmentExist.isPresent()) {
            return this.appointmentMapper.asOutput(this.appointmentRepository.findById(id).orElseThrow());
        }
        throw new RuntimeException("Girdiğiniz ID'ye sahip bir randevu bulunamadı.");
    }

    public List<AppointmentResponse> findAll() {
        return this.appointmentMapper.asOutput(this.appointmentRepository.findAll());
    }

    public AppointmentResponse save(LocalDate date, String hour, AppointmentRequest request) {
        String time = "";
        if(Integer.parseInt(hour) >= 24 && Integer.parseInt(hour) < 0) {
            throw new RuntimeException("Lütfen geçerli bir saat giriniz.");
        } else {
            if(Integer.parseInt(hour) < 10) {
                time = date + " 0" + hour + ":00";
            } else {
                time = date + " " + hour + ":00";
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Optional<Appointment> isAppointmentExist = this.appointmentRepository.findByAppointmentDateAndDoctorId(dateTime, request.getDoctor().getId());
        List<AvailableDate> availableDates = request.getDoctor().getAvailableDateList();
        for(AvailableDate obj : availableDates) {
            if(Objects.equals(obj.getAvailableDate(), date) && isAppointmentExist.isEmpty()) {
                return this.appointmentMapper.asOutput(this.appointmentRepository.save(this.appointmentMapper.asEntity(request)));
            }
        }
        throw new RuntimeException("Doktor " + request.getDoctor().getName() + " belirlediğiniz tarih ve saatte müsait değildir.");
    }

    public void delete(Long id) {
        Optional<Appointment> isAppointmentExist = this.appointmentRepository.findById(id);
        if(isAppointmentExist.isPresent()) {
            this.appointmentRepository.delete(isAppointmentExist.get());
        }
        throw new RuntimeException(id + " id'li randevu bulunamadı.");
    }

    public AppointmentResponse update(Long id, AppointmentRequest request) {
        Optional<Appointment> appointmentFromDb = this.appointmentRepository.findById(id);
        if(appointmentFromDb.isPresent()) {
            Appointment appointment = appointmentFromDb.get();
            this.appointmentMapper.update(appointment, request);
            return this.appointmentMapper.asOutput(this.appointmentRepository.save(appointment));
        }
        throw new RuntimeException("Güncellemeye çalıştığınız randevu sistemde bulunamadı.");
    }

    public List<AppointmentResponse> appointmentListByDoctorAndDateRange(String doctorName, LocalDate appointmentDateStart, LocalDate appointmentDateEnd) {
        LocalDateTime appointmentStart = appointmentDateStart.atStartOfDay();
        LocalDateTime appointmentEnd = appointmentDateEnd.atStartOfDay();
        List<Appointment> appointmentList= this.appointmentRepository.findByDoctorNameAndAppointmentDateBetween(doctorName, appointmentStart, appointmentEnd);

        if(appointmentList.isEmpty()) {
            throw new RuntimeException("Doktor " + doctorName + " için girilen zaman aralıklarında randevu yoktur.");
        }
        return this.appointmentMapper.asOutput(appointmentList);
    }

    public List<AppointmentResponse> appointmentListByAnimalAndDateRange(String animalName, LocalDate appointmentDateStart, LocalDate appointmentDateEnd) {
        LocalDateTime appointmentStart = appointmentDateStart.atStartOfDay();
        LocalDateTime appointmentEnd = appointmentDateEnd.atStartOfDay();
        List<Appointment> appointmentList= this.appointmentRepository.findByAnimalNameAndAppointmentDateBetween(animalName, appointmentStart, appointmentEnd);

        if(appointmentList.isEmpty()) {
            throw new RuntimeException(animalName + " için girilen zaman aralıklarında randevu yoktur.");
        }
        return this.appointmentMapper.asOutput(appointmentList);
    }
}
