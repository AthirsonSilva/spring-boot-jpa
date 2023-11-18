package com.amigoscode.service;

import com.amigoscode.entity.Customer;
import com.amigoscode.payload.CostumerPayload;
import com.amigoscode.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Integer id = 1;
        Customer customer = new Customer();
        customer.setId(id);
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(id);

        assertEquals(customer, result);
        verify(customerRepository).findById(id);
    }

    @Test
    void testCreate() {
        CostumerPayload request = generateCustomerPayload();

        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.create(request);

        assertEquals(customer, result);
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void testUpdate() {
        Integer id = 1;
        CostumerPayload request = generateCustomerPayload();

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.update(id, request);

        assertEquals(customer, result);
        verify(customerRepository).findById(id);
        verify(customerRepository).save(any(Customer.class));
    }

    private static CostumerPayload generateCustomerPayload() {
        return new CostumerPayload("John", "john@example.com", 30);
    }

    @Test
    void testDelete() {
        Integer id = 1;
        Customer customer = new Customer();
        customer.setId(id);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(any(Customer.class));

        customerService.delete(id);

        verify(customerRepository).findById(id);
        verify(customerRepository).delete(any(Customer.class));
    }

    @Test
    void testFindAll() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        Iterable<Customer> result = customerService.findAll();

        assertEquals(customers, result);
        verify(customerRepository).findAll();
    }
}