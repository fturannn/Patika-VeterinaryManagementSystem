package dev.patika.PatikaVeterinaryManagementSystem.api;

import dev.patika.PatikaVeterinaryManagementSystem.business.AnimalService;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AnimalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getById(@PathVariable("id") Long id) {
        return this.animalService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findAll() {
        return this.animalService.findAll();
    }

    // Kriter 11: Proje isterlerine göre hayvan kaydedilir
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalRequest request) {
        return this.animalService.save(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @PathVariable("id") Long id, @RequestBody AnimalRequest request) {
        return this.animalService.update(id, request);
    }

    // Kriter 16: Hayvanlar isme göre filtrelenir
    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findByName(@PathVariable("name") String name) {
        return this.animalService.findByName(name);
    }

    // Kriter 18: Girilen hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntülenmesi yapılır
    @GetMapping("/customerName={customerName}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findAnimalsByCustomerName(@PathVariable("customerName") String customerName) {
        return this.animalService.findAnimalsByCustomerName(customerName);
    }
}
