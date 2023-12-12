package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.core.exception.DuplicationException;
import dev.patika.PatikaVeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.Result;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultHelper;
import dev.patika.PatikaVeterinaryManagementSystem.core.utilies.Message;
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

    public ResultData<CustomerResponse> getById(Long id) {
        return ResultHelper.success(customerMapper.asOutput(customerRepository.findById(id).orElseThrow(() ->
                    new NotFoundException(Message.NOT_FOUND))));
    }

    public ResultData<CustomerResponse> save(CustomerRequest request) {
        Optional<Customer> isCustomerExist = this.customerRepository.findByNameAndPhone(request.getName(), request.getPhone());
        if(isCustomerExist.isEmpty()) {
            return ResultHelper.created(this.customerMapper.asOutput(this.customerRepository.save(customerMapper.asEntity(request))));
        }
        throw new DuplicationException("Eklemeye çalıştığınız müşteri daha önce eklenmiş");
    }

    public void delete(Long id) {
        customerRepository.delete(customerRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND)));
    }

    public ResultData<CustomerResponse> update(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        customerMapper.update(customer, request);
        return ResultHelper.success(this.customerMapper.asOutput(this.customerRepository.save(customer)));
    }

    public ResultData<List<CustomerResponse>> findAll() {
        return ResultHelper.success(this.customerMapper.asOutput(this.customerRepository.findAll()));
    }

    public ResultData<CustomerResponse> findByName(String name) {
        return ResultHelper.success(customerMapper.asOutput(customerRepository.findByName(name).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND))));
    }
}
