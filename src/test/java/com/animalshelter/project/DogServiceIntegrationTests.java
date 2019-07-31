package com.animalshelter.project;

import com.animalshelter.project.domain.Dog;
import com.animalshelter.project.exception.ResourceNotFoundException;
import com.animalshelter.project.service.DogService;
import com.animalshelter.project.transfer.CreateDogRequest;
import com.animalshelter.project.transfer.UpdateDogRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogServiceIntegrationTests {

    @Autowired
    private DogService dogService;

    @Test
    public void testCreateDog_whenValidRequest_thenReturnCreatedDog() {
        createDog();
    }

    private Dog createDog() {
        CreateDogRequest request = new CreateDogRequest();
        request.setName("Wolfy");
        request.setAge(2);
        request.setBreed("German Shepherd");
        request.setNeedsMedicalAttention(false);
        request.setSinceInShelter("2019-07-07");
        request.setWasAdopted(false);
        request.setWeight(32);

        Dog createdDog = dogService.createDog(request);

        assertThat(createdDog, notNullValue());
        assertThat(createdDog.getId(), greaterThan(0L));
        assertThat(createdDog.getName(), is(request.getName()));
        assertThat(createdDog.getAge(), is(request.getAge()));
        assertThat(createdDog.getBreed(), is(request.getBreed()));
        assertThat(createdDog.isNeedsMedicalAttention(), is(request.isNeedsMedicalAttention()));
        assertThat(createdDog.isWasAdopted(), is(request.isWasAdopted()));
        assertThat(createdDog.getSinceInShelter(), is(request.getSinceInShelter()));
        assertThat(createdDog.getWeight(), is(request.getWeight()));

        return createdDog;
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateDog_whenMissingMandatoryProperties_thenThrowException() {
        CreateDogRequest request = new CreateDogRequest();
        dogService.createDog(request);
    }

    @Test
    public void testGetDog_whenExistingId_thenReturnDog() throws ResourceNotFoundException {
        Dog createdDog = createDog();

        Dog dog = dogService.getDog(createdDog.getId());

        assertThat(dog, notNullValue());
        assertThat(dog.getId(), is(createdDog.getId()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetDog_whenNonExistingId_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        dogService.getDog(9999L);
    }

    @Test
    public void testUpdateDog_whenValidRequest_thenReturnUpdatedProduct() throws ResourceNotFoundException {
        Dog createdDog = createDog();

        UpdateDogRequest request = new UpdateDogRequest();
        request.setWasAdopted(true);
        request.setAge(3);
        request.setBreed("Wolfhound");
        request.setName("Blacky");
        request.setNeedsMedicalAttention(true);
        request.setWeight(56);
        request.setSinceInShelter("2019-08-08");

        Dog updatedDog = dogService.updateDog(createdDog.getId(), request);

        assertThat(updatedDog, notNullValue());
        assertThat(updatedDog.getId(), is(createdDog.getId()));

        assertThat(updatedDog.isWasAdopted(), not(is(createdDog.isWasAdopted())));
        assertThat(updatedDog.isWasAdopted(), is(request.isWasAdopted()));

        assertThat(updatedDog.getBreed(), not(is(createdDog.getBreed())));
        assertThat(updatedDog.getBreed(), is(request.getBreed()));

        assertThat(updatedDog.getName(), not(is(createdDog.getName())));
        assertThat(updatedDog.getName(), is(request.getName()));

        assertThat(updatedDog.getAge(), not(is(createdDog.getAge())));
        assertThat(updatedDog.getAge(), is(request.getAge()));

        assertThat(updatedDog.isNeedsMedicalAttention(), not(is(createdDog.isNeedsMedicalAttention())));
        assertThat(updatedDog.isNeedsMedicalAttention(), is(request.isNeedsMedicalAttention()));

        assertThat(updatedDog.getWeight(), not(is(createdDog.getWeight())));
        assertThat(updatedDog.getWeight(), is(request.getWeight()));

        assertThat(updatedDog.getSinceInShelter(), not(is(createdDog.getSinceInShelter())));
        assertThat(updatedDog.getSinceInShelter(), is(request.getSinceInShelter()));
    }
}
