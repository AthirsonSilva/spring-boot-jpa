package com.amigoscode.mapper;

import com.amigoscode.entity.Customer;
import com.amigoscode.payload.CostumerPayload;

public class CustomerMapper {
    public static Customer toCustomer(CostumerPayload customer) {
        Customer customerEntity = new Customer();

        customerEntity.setName(customer.name());
        customerEntity.setEmail(customer.email());
        customerEntity.setAge(customer.age());

        return customerEntity;
    }
}
