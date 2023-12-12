package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.core.exception.DuplicationException;
import dev.patika.PatikaVeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultHelper;
import dev.patika.PatikaVeterinaryManagementSystem.core.utilies.Message;
import dev.patika.PatikaVeterinaryManagementSystem.dao.AvailableDateRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AvailableDateResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.AvailableDate;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Doctor;
import dev.patika.PatikaVeterinaryManagementSystem.mapper.AvailableDateMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateService {
    private final AvailableDateRepository availableDateRepository;
    private final AvailableDateMapper availableDateMapper;

    public AvailableDateService(AvailableDateRepository availableDateRepository, AvailableDateMapper availableDateMapper) {
        this.availableDateRepository = availableDateRepository;
        this.availableDateMapper = availableDateMapper;
    }

    public ResultData<AvailableDateResponse> getById(Long id) {
        return ResultHelper.success(this.availableDateMapper.asOutput(this.availableDateRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND))));

    }

    public ResultData<List<AvailableDateResponse>> findAll() {
        return ResultHelper.success(this.availableDateMapper.asOutput(this.availableDateRepository.findAll()));
    }

    public ResultData<AvailableDateResponse> save(AvailableDateRequest request) {
        Optional<Doctor> isDoctorExist = this.availableDateRepository.findDoctorByDoctorId(request.getDoctor().getId());
        if(isDoctorExist.isEmpty()) {
            throw new NotFoundException("ID'si "+ request.getDoctor().getId() + " olan doktor bulunamadı");
        } else {
            Optional<AvailableDate> isAvailableDateExist = this.availableDateRepository.findByAvailableDateAndDoctorId(request.getAvailableDate(), request.getDoctor().getId());
            if(isAvailableDateExist.isEmpty()) {
                return ResultHelper.created(this.availableDateMapper.asOutput(this.availableDateRepository.save(this.availableDateMapper.asEntity(request))));
            }
            throw new DuplicationException("Eklemeye çalıştığınız tarih daha önce eklenmiş.");
        }
    }

    public void delete(Long id) {
        this.availableDateRepository.delete(this.availableDateRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND)));
    }

    public ResultData<AvailableDateResponse> update(Long id, AvailableDateRequest request) {
        Optional<Doctor> isDoctorExist = this.availableDateRepository.findDoctorByDoctorId(request.getDoctor().getId());
        if(isDoctorExist.isEmpty()) {
            throw new NotFoundException("ID'si "+ request.getDoctor().getId() + " olan doktor bulunamadı");
        } else {
            AvailableDate availableDate = this.availableDateRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
            this.availableDateMapper.update(availableDate, request);
            return ResultHelper.success(this.availableDateMapper.asOutput(this.availableDateRepository.save(availableDate)));
        }
    }
}
