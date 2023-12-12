package dev.patika.PatikaVeterinaryManagementSystem.api;

import dev.patika.PatikaVeterinaryManagementSystem.business.VaccineService;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultHelper;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.VaccineResponse;
import jakarta.validation.Valid;
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
    public ResultData<VaccineResponse> getById(@PathVariable("id") Long id) {
        return this.vaccineService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> findAll() {
        return this.vaccineService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineRequest request) {
        return this.vaccineService.save(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @PathVariable("id") Long id, @RequestBody VaccineRequest request) {
        return this.vaccineService.update(id, request);
    }

    @GetMapping("/upcomings")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> upcomingVaccines(@RequestParam("startDate") LocalDate startDate,
                                          @RequestParam("endDate") LocalDate endDate) {
        return this.vaccineService.upcomingVaccines(startDate, endDate);
    }

    @GetMapping("/animalId={animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getByAnimalId(@PathVariable("animalId") Long animalId) {
        return this.vaccineService.findByAnimalId(animalId);
    }
}
