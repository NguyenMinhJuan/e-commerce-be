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
    private ICustomerRepo customerRepo;

    @Override
    public Customer findByUser(User user) {
        return customerRepo.findByUser(user);
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepo.deleteById(id);
    }
}
