package com.amigoscode.repository;

import com.amigoscode.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Mock
    private CustomerRepository mockCustomerRepository;

    @Test
    public void testFindAll() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("John Doe");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Jane Doe");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(mockCustomerRepository.findAll()).thenReturn(customers);

        List<Customer> result = mockCustomerRepository.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        when(mockCustomerRepository.save(customer)).thenReturn(customer);

        Customer result = mockCustomerRepository.save(customer);
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testDelete() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");

        doNothing().when(mockCustomerRepository).delete(customer);
        mockCustomerRepository.delete(customer);

        verify(mockCustomerRepository, times(1)).delete(customer);
    }

    @Test
    public void testFindById() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");

        when(mockCustomerRepository.findById(1)).thenReturn(Optional.of(customer));

        Optional<Customer> result = mockCustomerRepository.findById(1);
        assertEquals("John Doe", result.get().getName());
    }
}