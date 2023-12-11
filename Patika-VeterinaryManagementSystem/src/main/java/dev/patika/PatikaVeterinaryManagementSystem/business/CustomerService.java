package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.CustomerRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.CustomerRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import dev.patika.PatikaVeterinaryManagementSystem.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerResponse getById(Long id) {
        Optional<Customer> isCustomerExist = this.customerRepository.findById(id);
        if(isCustomerExist.isPresent()) {
            return customerMapper.asOutput(customerRepository.findById(id).orElseThrow());
        }
        throw new RuntimeException("Girdiğiniz ID'ye sahip bir müşteri bulunamadı.");
    }

    public CustomerResponse save(CustomerRequest request) {
        Optional<Customer> isCustomerExist = this.customerRepository.findByNameAndPhone(request.getName(), request.getPhone());
        if(isCustomerExist.isEmpty()) {
            return this.customerMapper.asOutput(this.customerRepository.save(customerMapper.asEntity(request)));
        }
        throw new RuntimeException("Eklemeye çalıştığınız müşteri daha önce eklenmiş.");
    }

    public void delete(Long id) {
        Optional<Customer> isCustomerExist = customerRepository.findById(id);
        if(isCustomerExist.isPresent()) {
            customerRepository.delete(isCustomerExist.get());
        } else {
            throw new RuntimeException(id + " id'li müşteri bulunamadı.");
        }
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if(customerFromDb.isEmpty()) {
            throw new RuntimeException("Güncellemeye çalıştığınız müşteri sistemde bulunamadı.");
        } else {
            Customer customer = customerFromDb.get();
            customerMapper.update(customer, request);
            return this.customerMapper.asOutput(this.customerRepository.save(customer));
        }
    }

    public List<CustomerResponse> findAll() {
        return this.customerMapper.asOutput(this.customerRepository.findAll());
    }

    public CustomerResponse findByName(String name) {
        Optional<Customer> customerFromDb = customerRepository.findByName(name);
        if (customerFromDb.isEmpty()) {
            throw new RuntimeException(name + " isimli müşteri sistemde bulunamadı.");
        } else {
            return customerMapper.asOutput(customerRepository.findByName(name).orElseThrow());
        }
    }
}
