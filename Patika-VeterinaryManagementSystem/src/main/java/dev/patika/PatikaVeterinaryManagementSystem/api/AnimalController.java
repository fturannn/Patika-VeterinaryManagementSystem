package dev.patika.PatikaVeterinaryManagementSystem.api;

import dev.patika.PatikaVeterinaryManagementSystem.business.AnimalService;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AnimalResponse;
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
    public AnimalResponse getById(@PathVariable("id") Long id) {
        return this.animalService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAll() {
        return this.animalService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse save(@RequestBody AnimalRequest request) {
        return this.animalService.save(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse update(@PathVariable("id") Long id, @RequestBody AnimalRequest request) {
        return this.animalService.update(id, request);
    }

    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse findByName(@PathVariable("name") String name) {
        return this.animalService.findByName(name);
    }

    @GetMapping("/customerName={customerName}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAnimalsByCustomerName(@PathVariable("customerName") String customerName) {
        return this.animalService.findAnimalsByCustomerName(customerName);
    }
}
