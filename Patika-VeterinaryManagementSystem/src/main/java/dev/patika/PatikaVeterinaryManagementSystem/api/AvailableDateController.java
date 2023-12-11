package dev.patika.PatikaVeterinaryManagementSystem.api;

import dev.patika.PatikaVeterinaryManagementSystem.business.AvailableDateService;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AvailableDateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/availableDates")
public class AvailableDateController {
    private final AvailableDateService availableDateService;

    public AvailableDateController(AvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse getById(@PathVariable("id") Long id) {
        return this.availableDateService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateResponse> findAll() {
        return this.availableDateService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse save(@RequestBody AvailableDateRequest request) {
        return this.availableDateService.save(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse update(@PathVariable("id") Long id, @RequestBody AvailableDateRequest request) {
        return this.availableDateService.update(id, request);
    }
}
