package com.animalshelter.project.service;

import com.animalshelter.project.domain.Customer;
import com.animalshelter.project.exception.ResourceNotFoundException;
import com.animalshelter.project.repository.CustomerRepository;
import com.animalshelter.project.transfer.CreateCustomerRequest;
import com.animalshelter.project.transfer.GetCustomersRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CustomerService.class);

    // IoC (inversion of control) and dependency injection
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        LOGGER.info("Creating customer {}", request);

        Customer customer = objectMapper.convertValue(request, Customer.class);

        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving customer {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer " + id + " does not exist."));
    }

    public Page<Customer> getCustomers(GetCustomersRequest request, Pageable pageable) {
        LOGGER.info("Retrieving customers {}", request);

        if (request.getPartialFirstName() != null) {
            return customerRepository.findByPartialFirstName(
                    request.getPartialFirstName(),
                    pageable);
        }

        return customerRepository.findAll(pageable);
    }
}
