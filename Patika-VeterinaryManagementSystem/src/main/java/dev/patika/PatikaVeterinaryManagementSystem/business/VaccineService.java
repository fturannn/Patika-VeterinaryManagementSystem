package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.PatikaVeterinaryManagementSystem.core.exception.VaccinationException;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultHelper;
import dev.patika.PatikaVeterinaryManagementSystem.core.utilies.Message;
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

    public ResultData<VaccineResponse> getById(Long id) {
        return ResultHelper.success(this.vaccineMapper.asOutput(this.vaccineRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND))));
    }

    public ResultData<List<VaccineResponse>> findAll() {
        return ResultHelper.success(this.vaccineMapper.asOutput(this.vaccineRepository.findAll()));
    }

    public ResultData<VaccineResponse> save(VaccineRequest request) {
        List<Vaccine> vaccineList = this.vaccineRepository.findByCodeAndAnimalId(request.getCode(), request.getAnimal().getId());
        if(!vaccineList.isEmpty() && vaccineList.getLast().getProtectionFinishDate().isAfter(LocalDate.now())) {
            throw new VaccinationException("Bu hastaya " + request.getName() + " aşısı uygulanmış ve koruyuculuk süresi devam ettiğinden sisteme yeniden girilemez!");
        } else {
            return ResultHelper.created(this.vaccineMapper.asOutput(this.vaccineRepository.save(vaccineMapper.asEntity(request))));
        }
    }

    public void delete(Long id) {
        this.vaccineRepository.delete(this.vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    public ResultData<VaccineResponse> update(Long id, VaccineRequest request) {
        Vaccine vaccine = this.vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        vaccineMapper.update(vaccine, request);
        return ResultHelper.success(vaccineMapper.asOutput(vaccineRepository.save(vaccine)));
    }

    public ResultData<List<VaccineResponse>> upcomingVaccines(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> allVaccines = this.vaccineRepository.findAll();
        List<Vaccine> upcomings = new ArrayList<>();
        for(Vaccine obj : allVaccines) {
            if(startDate.isBefore(obj.getProtectionFinishDate()) && endDate.isAfter(obj.getProtectionFinishDate())) {
                upcomings.add(obj);
            }
        }
        if(upcomings.isEmpty()) {
            throw new NotFoundException("Girilen aralıkta aşı bulunamadı");
        }
        return ResultHelper.success(this.vaccineMapper.asOutput(upcomings));
    }

    public ResultData<List<VaccineResponse>> findByAnimalId(Long animalId) {
        List<Vaccine> vaccineList = this.vaccineRepository.findByAnimalId(animalId);
        if(!vaccineList.isEmpty()) {
            return ResultHelper.success(this.vaccineMapper.asOutput(this.vaccineRepository.findByAnimalId(animalId)));
        }
        throw new NotFoundException(Message.NOT_FOUND);
    }
}
