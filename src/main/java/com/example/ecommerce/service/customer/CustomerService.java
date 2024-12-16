package com.example.ecommerce.service.customer;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepo customerRepo;

    @Override
    public Iterable<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Customer findByUser(User user) {
        return customerRepo.findByUser(user);
    }
}
