package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.VaccineRepository;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Vaccine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineService {
    private final VaccineRepository vaccineRepository;

    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public Vaccine getById(Long id) {
        return this.vaccineRepository.findById(id).orElseThrow();
    }

    public String save(Vaccine vaccine) {
        List<Vaccine> vaccineList = this.vaccineRepository.findByVaccineNameAndVaccineCode(vaccine.getName(), vaccine.getCode());
        if(!vaccineList.isEmpty() && vaccineList.getLast().getProtectionFinishDate().isAfter(LocalDate.now())) {
            return "Bu hastaya " + vaccine.getName() + " aşısı uygulanmış ve koruyuculuk süresi devam ettiğinden sisteme yeniden girilemez!";
        } else {
            this.vaccineRepository.save(vaccine);
            return "Aşı ekleme işlemi başarılı!";
        }
    }

    public void delete(Long id) {
        this.vaccineRepository.delete(this.getById(id));
    }
}
