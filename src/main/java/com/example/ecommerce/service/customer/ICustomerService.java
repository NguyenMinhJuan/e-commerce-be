package com.example.ecommerce.service.customer;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.IGenericService;

import java.util.Optional;

public interface ICustomerService extends IGenericService<Customer> {
    Customer findByUser(User user);
}
