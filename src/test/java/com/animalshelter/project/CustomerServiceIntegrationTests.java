package com.animalshelter.project;

import com.animalshelter.project.domain.Customer;
import com.animalshelter.project.exception.ResourceNotFoundException;
import com.animalshelter.project.service.CustomerService;
import com.animalshelter.project.transfer.CreateCustomerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomer_whenValidRequest_thenReturnCreatedCustomer() {
        createCustomer();
    }


    private Customer createCustomer() {
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setFirstName("Test");
        request.setLastName("Customer");
        request.setSsn(123456789);

        Customer createdCustomer = customerService.createCustomer(request);

        assertThat(createdCustomer, notNullValue());
        assertThat(createdCustomer.getId(), greaterThan(0L));
        assertThat(createdCustomer.getFirstName(), is(request.getFirstName()));
        assertThat(createdCustomer.getLastName(), is(request.getLastName()));
        assertThat(createdCustomer.getSsn(), is(request.getSsn()));

        return createdCustomer;
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateCustomer_whenMissingMandatoryProperties_thenThrowException() {
        CreateCustomerRequest request = new CreateCustomerRequest();
        customerService.createCustomer(request);
    }

    @Test
    public void testGetCustomer_whenExistingId_thenReturnCustomer() throws ResourceNotFoundException {
        Customer createdCustomer = createCustomer();

        Customer customer = customerService.getCustomer(createdCustomer.getId());

        assertThat(customer, notNullValue());
        assertThat(customer.getId(), is(createdCustomer.getId()));
    }
}
