package com.example.ecommerce.repository;

import com.example.ecommerce.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerOrderRepo extends JpaRepository<CustomerOrder, Long> {
}
