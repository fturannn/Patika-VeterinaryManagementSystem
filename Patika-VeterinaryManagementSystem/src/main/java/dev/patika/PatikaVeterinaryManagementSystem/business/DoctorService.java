package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.core.exception.DuplicationException;
import dev.patika.PatikaVeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultHelper;
import dev.patika.PatikaVeterinaryManagementSystem.core.utilies.Message;
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

    public ResultData<DoctorResponse> getById(Long id) {
        return ResultHelper.success(this.doctorMapper.asOutput(this.doctorRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND))));
    }

    public ResultData<List<DoctorResponse>> findAll() {
        return ResultHelper.success(this.doctorMapper.asOutput(this.doctorRepository.findAll()));
    }

    public ResultData<DoctorResponse> save(DoctorRequest request) {
        Optional<Doctor> isDoctorExist = this.doctorRepository.findByNameAndPhone(request.getName(), request.getPhone());
        if(isDoctorExist.isEmpty()) {
            return ResultHelper.created(this.doctorMapper.asOutput(this.doctorRepository.save(this.doctorMapper.asEntity(request))));
        }
        throw new DuplicationException("Eklemeye çalıştığınız doktor daha önce eklenmiş.");
    }

    public void delete(Long id) {
        this.doctorRepository.delete(this.doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    public ResultData<DoctorResponse> update(Long id, DoctorRequest request) {
        Doctor doctor = this.doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        doctorMapper.update(doctor, request);
        return ResultHelper.success(this.doctorMapper.asOutput(this.doctorRepository.save(doctor)));
    }
}
