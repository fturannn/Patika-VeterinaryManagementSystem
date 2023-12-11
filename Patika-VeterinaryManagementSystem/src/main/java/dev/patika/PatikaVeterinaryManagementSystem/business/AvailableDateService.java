package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.AvailableDateRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AvailableDateResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.AvailableDate;
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

    public AvailableDateResponse getById(Long id) {
        Optional<AvailableDate> isAvailableDateExist = this.availableDateRepository.findById(id);
        if(isAvailableDateExist.isPresent()) {
            return this.availableDateMapper.asOutput(this.availableDateRepository.findById(id).orElseThrow());
        }
        throw new RuntimeException("Girdiğiniz ID'li bir müsait gün bulunamadı.");
    }

    public List<AvailableDateResponse> findAll() {
        return this.availableDateMapper.asOutput(this.availableDateRepository.findAll());
    }

    public AvailableDateResponse save(AvailableDateRequest request) {
        Optional<AvailableDate> isAvailableDateExist = this.availableDateRepository.findByAvailableDate(request.getAvailableDate());
        if(isAvailableDateExist.isEmpty()) {
            return this.availableDateMapper.asOutput(this.availableDateRepository.save(this.availableDateMapper.asEntity(request)));
        }
        throw new RuntimeException("Eklemeye çalıştığınız tarih daha önce eklenmiş.");
    }

    public void delete(Long id) {
        Optional<AvailableDate> isAvailableDateExist = this.availableDateRepository.findById(id);
        if(isAvailableDateExist.isPresent()) {
            this.availableDateRepository.delete(isAvailableDateExist.get());
        }
        throw new RuntimeException(id + " id'li müsait gün bulunamadı.");
    }

    public AvailableDateResponse update(Long id, AvailableDateRequest request) {
        Optional<AvailableDate> availableDateFromDb = this.availableDateRepository.findById(id);
        if(availableDateFromDb.isPresent()) {
            AvailableDate availableDate = availableDateFromDb.get();
            this.availableDateMapper.update(availableDate, request);
            return this.availableDateMapper.asOutput(this.availableDateRepository.save(availableDate));
        }
        throw new RuntimeException("Güncellemeye çalıştığınız tarih sistemde bulunamadı.");
    }
}
