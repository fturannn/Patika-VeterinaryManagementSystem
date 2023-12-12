package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.core.exception.DuplicationException;
import dev.patika.PatikaVeterinaryManagementSystem.core.exception.NotFoundException;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultData;
import dev.patika.PatikaVeterinaryManagementSystem.core.result.ResultHelper;
import dev.patika.PatikaVeterinaryManagementSystem.core.utilies.Message;
import dev.patika.PatikaVeterinaryManagementSystem.dao.AnimalRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Customer;
import dev.patika.PatikaVeterinaryManagementSystem.mapper.AnimalMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public AnimalService(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    public ResultData<AnimalResponse> getById(Long id) {
        return ResultHelper.success(this.animalMapper.asOutput(this.animalRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND))));
    }

    public ResultData<List<AnimalResponse>> findAll() {
        return ResultHelper.success(this.animalMapper.asOutput(this.animalRepository.findAll()));
    }

    public ResultData<AnimalResponse> save(AnimalRequest request) {
        Optional<Customer> isCustomerExist = this.animalRepository.findCustomerByCustomerId(request.getCustomer().getId());
        if(isCustomerExist.isEmpty()) {
            throw new NotFoundException("ID'si "+ request.getCustomer().getId() + " olan müşteri bulunamadı");
        } else {
            Optional<Animal> isAnimalExist = this.animalRepository.findByNameAndSpeciesAndBreed(request.getName(), request.getSpecies(), request.getBreed());
            if(isAnimalExist.isEmpty()) {
                return ResultHelper.created(this.animalMapper.asOutput(this.animalRepository.save(this.animalMapper.asEntity(request))));
            }
            throw new DuplicationException("Eklemeye çalıştığınız hayvan daha önce eklenmiş.");
        }
    }

    public void delete(Long id) {
        animalRepository.delete(this.animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    public ResultData<AnimalResponse> update(Long id, AnimalRequest request) {
        Optional<Customer> isCustomerExist = this.animalRepository.findCustomerByCustomerId(request.getCustomer().getId());
        if(isCustomerExist.isEmpty()) {
            throw new NotFoundException("ID'si "+ request.getCustomer().getId() + " olan müşteri bulunamadı");
        } else {
            Animal animal = this.animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
            animalMapper.update(animal, request);
            return ResultHelper.success(this.animalMapper.asOutput(animalRepository.save(animal)));
        }
    }

    public ResultData<AnimalResponse> findByName(String name) {
        Animal animal = this.animalRepository.findByName(name).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        return ResultHelper.success(this.animalMapper.asOutput(animal));
    }

    public ResultData<List<AnimalResponse>> findAnimalsByCustomerName(String customerName) {
        List<Animal> animalList = this.animalRepository.findByCustomerName(customerName);
        if(!animalList.isEmpty()) {
            return ResultHelper.success(this.animalMapper.asOutput(this.animalRepository.findByCustomerName(customerName)));
        }
        throw new NotFoundException(Message.NOT_FOUND);
    }
}
