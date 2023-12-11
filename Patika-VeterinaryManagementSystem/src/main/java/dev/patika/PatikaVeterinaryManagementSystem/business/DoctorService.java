package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.DoctorRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.DoctorRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.DoctorResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Doctor;
import dev.patika.PatikaVeterinaryManagementSystem.mapper.DoctorMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public DoctorResponse getById(Long id) {
        Optional<Doctor> isDoctorExist = this.doctorRepository.findById(id);
        if(isDoctorExist.isPresent()) {
            return this.doctorMapper.asOutput(this.doctorRepository.findById(id).orElseThrow());
        }
        throw new RuntimeException("Girdiğiniz ID'ye sahip bir doktor bulunamadı.");
    }

    public List<DoctorResponse> findAll() {
        return this.doctorMapper.asOutput(this.doctorRepository.findAll());
    }

    public DoctorResponse save(DoctorRequest request) {
        Optional<Doctor> isDoctorExist = this.doctorRepository.findByNameAndPhone(request.getName(), request.getPhone());
        if(isDoctorExist.isPresent()) {
            throw new RuntimeException("Eklemeye çalıştığınız doktor daha önce eklenmiş.");
        }
        return this.doctorMapper.asOutput(this.doctorRepository.save(this.doctorMapper.asEntity(request)));
    }

    public void delete(Long id) {
        Optional<Doctor> isDoctorExist = this.doctorRepository.findById(id);
        if(isDoctorExist.isPresent()) {
            this.doctorRepository.delete(isDoctorExist.get());
        }
        throw new RuntimeException(id + " id'li doktor bulunamadı.");
    }

    public DoctorResponse update(Long id, DoctorRequest request) {
        Optional<Doctor> doctorFromDb = this.doctorRepository.findById(id);
        if(doctorFromDb.isPresent()) {
            Doctor doctor = doctorFromDb.get();
            doctorMapper.update(doctor, request);
            return this.doctorMapper.asOutput(this.doctorRepository.save(doctor));
        }
        throw new RuntimeException("Güncellemeye çalıştığınız doktor sistemde bulunamadı.");
    }
}
