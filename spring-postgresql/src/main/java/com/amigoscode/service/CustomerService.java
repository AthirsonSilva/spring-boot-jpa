package com.amigoscode.service;

import com.amigoscode.entity.Customer;
import com.amigoscode.exception.CustomerNotFound;
import com.amigoscode.payload.CostumerPayload;
import com.amigoscode.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.amigoscode.mapper.CustomerMapper.toCustomer;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void delete(Integer id) {
        customerRepository.findById(id).ifPresent(customerRepository::delete);
    }

    public Customer update(Integer id, CostumerPayload request) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFound("Customer not found")
        );

        customer.setName(request.name() == null ? customer.getName() : request.name());
        customer.setEmail(request.email() == null ? customer.getEmail() : request.email());
        customer.setAge(request.age() == null ? customer.getAge() : request.age());

        return customerRepository.save(customer);
    }

    public Customer create(CostumerPayload request) {
        return customerRepository.save(toCustomer(request));
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFound("Customer not found")
        );
    }
}
