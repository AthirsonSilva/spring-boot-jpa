package com.amigoscode.controller;

import com.amigoscode.entity.Customer;
import com.amigoscode.payload.CostumerPayload;
import com.amigoscode.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService mockCustomerService;

    @Test
    public void testGetCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("John Doe");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Jane Doe");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(mockCustomerService.findAll()).thenReturn(customers);

        ResponseEntity<Iterable<Customer>> response = customerController.getCustomers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((List<Customer>) response.getBody()).size());
    }

    @Test
    public void testGetCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");

        when(mockCustomerService.findById(1)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.getCustomer(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", Objects.requireNonNull(response.getBody()).getName());
    }

    @Test
    public void testCreateCustomer() {
        CostumerPayload request = new CostumerPayload("John Doe", "john.doe@example.com", 30);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAge(30);

        when(mockCustomerService.create(request)).thenReturn(customer);

        ResponseEntity<?> response = customerController.createCustomer(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(mockCustomerService).delete(1);
        ResponseEntity<?> response = customerController.deleteCustomer(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testUpdateCustomer() {
        CostumerPayload request = new CostumerPayload("Jane Doe", "jane.doe@example.com", 25);

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1);
        updatedCustomer.setName("Jane Doe");
        updatedCustomer.setEmail("jane.doe@example.com");
        updatedCustomer.setAge(25);

        when(mockCustomerService.update(1, request)).thenReturn(updatedCustomer);

        ResponseEntity<Customer> response = customerController.updateCustomer(1, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jane Doe", Objects.requireNonNull(response.getBody()).getName());
    }
}