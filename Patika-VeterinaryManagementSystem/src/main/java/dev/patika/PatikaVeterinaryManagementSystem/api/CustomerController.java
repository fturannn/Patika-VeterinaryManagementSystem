package dev.patika.PatikaVeterinaryManagementSystem.api;

import dev.patika.PatikaVeterinaryManagementSystem.business.CustomerService;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.CustomerRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getById(@PathVariable("id") Long id) {
        return this.customerService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> findAll() {
        return this.customerService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse save(@RequestBody CustomerRequest request) {
        return this.customerService.save(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse update(@PathVariable("id") Long id, @RequestBody CustomerRequest request) {
        return this.customerService.update(id, request);
    }

    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getByName(@PathVariable("name") String name) {
        return this.customerService.findByName(name);
    }
}
