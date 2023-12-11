package dev.patika.PatikaVeterinaryManagementSystem.business;

import dev.patika.PatikaVeterinaryManagementSystem.dao.AnimalRepository;
import dev.patika.PatikaVeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.PatikaVeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.PatikaVeterinaryManagementSystem.entities.Animal;
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

    public AnimalResponse getById(Long id) {
        Optional<Animal> isAnimalExist = this.animalRepository.findById(id);
        if(isAnimalExist.isPresent()) {
            return this.animalMapper.asOutput(this.animalRepository.findById(id).orElseThrow());
        }
        throw new RuntimeException("Girdiğiniz ID'ye ait bir hayvan bulunamadı.");
    }

    public List<AnimalResponse> findAll() {
        return this.animalMapper.asOutput(this.animalRepository.findAll());
    }

    public AnimalResponse save(AnimalRequest request) {
        Optional<Animal> isAnimalExist = this.animalRepository.findById(request.getId());
        if(isAnimalExist.isEmpty()) {
            return this.animalMapper.asOutput(this.animalRepository.save(animalMapper.asEntity(request)));
        }
        throw new RuntimeException("Eklemeye çalıştığınız hayvan daha önce eklenmiş.");
    }

    public void delete(Long id) {
        Optional<Animal> isAnimalExist = this.animalRepository.findById(id);
        if(isAnimalExist.isPresent()) {
            animalRepository.delete(isAnimalExist.get());
        }
        throw new RuntimeException(id + "'li hayvan bulunamadı.");
    }

    public AnimalResponse update(Long id, AnimalRequest request) {
        Optional<Animal> animalFromDb = this.animalRepository.findById(id);
        if(animalFromDb.isPresent()) {
            Animal animal = animalFromDb.get();
            animalMapper.update(animal, request);
            return this.animalMapper.asOutput(animalRepository.save(animal));
        }
        throw new RuntimeException("Güncellemeye çalıştığınız hayvan sistemde bulunamadı.");
    }

    public AnimalResponse findByName(String name) {
        Optional<Animal> animalFromDb = this.animalRepository.findByName(name);
        if(animalFromDb.isPresent()) {
            return this.animalMapper.asOutput(animalFromDb.get());
        }
        throw new RuntimeException(name + " isimli hayvan sistemde bulunamadı.");
    }

    public List<AnimalResponse> findAnimalsByCustomerName(String customerName) {
        List<Animal> animalList = this.animalRepository.findAnimalsByCustomerName(customerName);
        if(!animalList.isEmpty()) {
            return this.animalMapper.asOutput(this.animalRepository.findAnimalsByCustomerName(customerName));
        }
        throw new RuntimeException(customerName + " isimli kullanıcıya ait hayvan bulunamadı.");
    }
}
