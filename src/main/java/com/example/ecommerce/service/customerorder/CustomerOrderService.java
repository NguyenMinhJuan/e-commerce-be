package com.example.ecommerce.service.customerorder;

import com.example.ecommerce.model.CustomerOrder;
import com.example.ecommerce.repository.ICustomerOrderRepo;
import com.example.ecommerce.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerOrderService implements ICustomerOrderService {
    @Autowired
    private ICustomerOrderRepo customerOrderRepo;

    @Override
    public Iterable<CustomerOrder> findAll() {
        return customerOrderRepo.findAll();
    }

    @Override
    public Optional<CustomerOrder> findById(Long id) {
        return customerOrderRepo.findById(id);
    }

    @Override
    public void save(CustomerOrder customerOrder) {
        customerOrderRepo.save(customerOrder);
    }

    @Override
    public void delete(Long id) {
        customerOrderRepo.deleteById(id);
    }
}
