package com.animalshelter.project.web;

import com.animalshelter.project.domain.Customer;
import com.animalshelter.project.exception.ResourceNotFoundException;
import com.animalshelter.project.service.CustomerService;
import com.animalshelter.project.transfer.CreateCustomerRequest;
import com.animalshelter.project.transfer.GetCustomersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // endpoint
    @GetMapping
    public ResponseEntity<Page<Customer>> getCustomers(
            GetCustomersRequest request, Pageable pageable) {
        Page<Customer> response = customerService.getCustomers(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        Customer response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
