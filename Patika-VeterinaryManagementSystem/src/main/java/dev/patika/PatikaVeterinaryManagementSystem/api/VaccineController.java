package dev.patika.PatikaVeterinaryManagementSystem.api;

import dev.patika.PatikaVeterinaryManagementSystem.business.VaccineService;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Vaccine;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine getById(@PathVariable("id") Long id) {
        return this.vaccineService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Vaccine vaccine) {
        return this.vaccineService.save(vaccine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Vaccine update(@RequestBody Vaccine vaccine) {
        return this.vaccineService.update(vaccine);
    }

    @GetMapping("/({startDate})-({endDate})")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> upcomingVaccines(@PathVariable("startDate") LocalDate startDate,
                                          @PathVariable("endDate") LocalDate endDate) {
        return this.vaccineService.upcomingVaccines(startDate, endDate);
    }

    @GetMapping("/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> getByAnimalId(@PathVariable("animalId") Long animalId) {
        return this.vaccineService.findByAnimalId(animalId);
    }
}
