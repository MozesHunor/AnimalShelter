package com.animalshelter.project.web;

import com.animalshelter.project.domain.Dog;
import com.animalshelter.project.exception.ResourceNotFoundException;
import com.animalshelter.project.service.DogService;
import com.animalshelter.project.transfer.CreateDogRequest;
import com.animalshelter.project.transfer.GetDogsRequest;
import com.animalshelter.project.transfer.UpdateDogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class DogController {

    private final DogService dogService;

    @Autowired
    public DogController(DogService productService) {
        this.dogService = productService;
    }

    // endpoint
    @GetMapping
    public ResponseEntity<Page<Dog>> getDogs(
            GetDogsRequest request, Pageable pageable) {
        Page<Dog> response = dogService.getDogs(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody @Valid CreateDogRequest request) {
        Dog response = dogService.createDog(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDog(@PathVariable("id") Long id) {
        dogService.deleteDog(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDog(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Dog dog = dogService.getDog(id);
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDog(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateDogRequest request) throws ResourceNotFoundException {
        dogService.updateDog(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
