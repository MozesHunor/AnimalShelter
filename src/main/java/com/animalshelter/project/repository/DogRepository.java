package com.animalshelter.project.repository;

import com.animalshelter.project.domain.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DogRepository extends JpaRepository<Dog, Long> {

    // queries derived from method names
    Page<Dog> findByNameContaining(String partialName, Pageable pageable);


    @Query(value = "SELECT * FROM dog WHERE breed LIKE '%?1'", nativeQuery = true)
    Page<Dog> findByBreed(String breed, Pageable pageable);

    @Query(value = "SELECT * FROM dog WHERE name LIKE '%?1'", nativeQuery = true)
    Page<Dog> findByPartialName(String partialName, Pageable pageable);
}
