package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.CustomerRepository;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getById(Long id) {
        return this.customerRepository.findById(id).orElseThrow();
    }

    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public void delete(Long id) {
        this.customerRepository.delete(this.getById(id));
    }

    public Customer update(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public Optional<Customer> findByName(String name) {
        return this.customerRepository.findByName(name);
    }
}
