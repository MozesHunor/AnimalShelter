package com.animalshelter.project.service;

import com.animalshelter.project.domain.Dog;
import com.animalshelter.project.exception.ResourceNotFoundException;
import com.animalshelter.project.repository.DogRepository;
import com.animalshelter.project.transfer.CreateDogRequest;
import com.animalshelter.project.transfer.GetDogsRequest;
import com.animalshelter.project.transfer.UpdateDogRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DogService.class);

    // IoC (inversion of control) and dependency injection
    private final DogRepository dogRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public DogService(DogRepository dogRepository, ObjectMapper objectMapper) {
        this.dogRepository = dogRepository;
        this.objectMapper = objectMapper;
    }

    public Dog createDog(CreateDogRequest request) {
        LOGGER.info("Creating dog {}", request);

        Dog dog = objectMapper.convertValue(request, Dog.class);
        return dogRepository.save(dog);
    }

    public Dog getDog(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving dog {}", id);
        // using Optional with orElseThrow
        return dogRepository.findById(id)
                // using Lambda expressions
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product " + id + " does not exist."));
    }

    public Dog updateDog(long id, UpdateDogRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating dog {} with {}", id, request);

        Dog dog = getDog(id);

        BeanUtils.copyProperties(request, dog);

        return dogRepository.save(dog);
    }

    public void deleteDog(long id) {
        LOGGER.info("Deleting dog {}", id);
        dogRepository.deleteById(id);
        LOGGER.info("Deleted dog {}", id);
    }

    public Page<Dog> getDogs(GetDogsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving dogs {}", request);

        if (request.getPartialName() != null) {
            return dogRepository.findByPartialName(
                    request.getPartialName(),
                    pageable);
        } else if (request.getBreed() != null) {
            return dogRepository.findByBreed(
                    request.getBreed(), pageable);
        }

        return dogRepository.findAll(pageable);
    }
}
