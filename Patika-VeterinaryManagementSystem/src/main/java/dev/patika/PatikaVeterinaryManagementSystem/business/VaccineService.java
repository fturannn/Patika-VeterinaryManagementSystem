package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.VaccineRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Vaccine;
import dev.patika.PatikaVeterinaryManagementSystem.mapper.VaccineMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;

    public VaccineService(VaccineRepository vaccineRepository, VaccineMapper vaccineMapper) {
        this.vaccineRepository = vaccineRepository;
        this.vaccineMapper = vaccineMapper;
    }

    public VaccineResponse getById(Long id) {
        Optional<Vaccine> isVaccineExist = this.vaccineRepository.findById(id);
        if(isVaccineExist.isPresent()) {
            return this.vaccineMapper.asOutput(this.vaccineRepository.findById(id).orElseThrow());
        }
        throw new RuntimeException("Girdiğiniz ID'ye sahip bir aşı bulunamadı.");
    }

    public VaccineResponse save(VaccineRequest request) {
        List<Vaccine> vaccineList = this.vaccineRepository.findByCodeAndAnimalId(request.getCode(), request.getAnimal().getId());
        if(!vaccineList.isEmpty() && vaccineList.getLast().getProtectionFinishDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Bu hastaya " + request.getName() + " aşısı uygulanmış ve koruyuculuk süresi devam ettiğinden sisteme yeniden girilemez!");
        } else {
            return this.vaccineMapper.asOutput(this.vaccineRepository.save(vaccineMapper.asEntity(request)));
        }
    }

    public void delete(Long id) {
        Optional<Vaccine> isVaccineExist = this.vaccineRepository.findById(id);
        if(isVaccineExist.isPresent()) {
            this.vaccineRepository.delete(isVaccineExist.get());
        }else {
            throw new RuntimeException(id + " id'li aşı bulunamadı.");
        }
    }

    public VaccineResponse update(Long id, VaccineRequest request) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if(vaccineFromDb.isEmpty()) {
            throw new RuntimeException("Güncellemeye çalıştığınız aşı sistemde bulunamadı.");
        } else {
            Vaccine vaccine = vaccineFromDb.get();
            vaccineMapper.update(vaccine, request);
            return vaccineMapper.asOutput(vaccineRepository.save(vaccine));
        }
    }

    public List<VaccineResponse> findAll() {
        return this.vaccineMapper.asOutput(this.vaccineRepository.findAll());
    }

    public List<VaccineResponse> upcomingVaccines(LocalDate startDate, LocalDate endDate) {
        List<VaccineResponse> allVaccines = this.findAll();
        List<VaccineResponse> upcomings = new ArrayList<>();
        for(VaccineResponse obj : allVaccines) {
            if(startDate.isBefore(obj.getProtectionFinishDate()) && endDate.isAfter(obj.getProtectionFinishDate())) {
                upcomings.add(obj);
            } else {
                throw new RuntimeException("Girilen aralıkta aşı bulunamadı.");
            }
        }
        return upcomings;
    }

    public List<VaccineResponse> findByAnimalId(Long id) {
        return this.vaccineMapper.asOutput(this.vaccineRepository.findByAnimalId(id));
    }
}
