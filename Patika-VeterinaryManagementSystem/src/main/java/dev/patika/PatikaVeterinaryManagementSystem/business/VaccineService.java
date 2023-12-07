package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.VaccineRepository;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Vaccine;
import org.springframework.stereotype.Service;

import java.time.Duration;
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

    public Vaccine update(Vaccine vaccine) {
        return this.vaccineRepository.save(vaccine);
    }

    public List<Vaccine> findAll() {
        return this.vaccineRepository.findAll();
    }

    public List<Vaccine> upcomingVaccines(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> allVaccines = this.findAll();
        List<Vaccine> upcomings = null;
        for(Vaccine obj : allVaccines) {
            if(startDate.isBefore(obj.getProtectionFinishDate()) && endDate.isAfter(obj.getProtectionFinishDate())) {
                long daysBetween = Duration.between(obj.getProtectionFinishDate(), endDate).toDays();
                if(daysBetween <= 7) {
                    upcomings.add(obj);
                }
            }
        }
        return upcomings;
    }

    public List<Vaccine> findByAnimalId(Long id) {
        return this.vaccineRepository.findByAnimalId(id);
    }
}
