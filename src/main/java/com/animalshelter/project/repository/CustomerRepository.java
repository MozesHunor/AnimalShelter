package com.animalshelter.project.repository;

import com.animalshelter.project.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByFirstNameContaining(String partialFirstName, Pageable pageable);

    @Query(value = "SELECT * FROM customer WHERE firstName LIKE '%?1'", nativeQuery = true)
    Page<Customer> findByPartialFirstName(String partialFirstName, Pageable pageable);
}
