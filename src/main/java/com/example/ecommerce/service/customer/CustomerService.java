package com.example.ecommerce.service.customer;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    @Override
    public Iterable<Customer> findAll() {
    }

    @Override
    public Optional<Customer> findById(Long id) {
    }

    @Override
    public void save(Customer customer) {
    }

    @Override
    public void delete(Long id) {
    }
}
