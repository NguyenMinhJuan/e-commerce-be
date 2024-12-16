package com.example.ecommerce.repository;

import com.example.ecommerce.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteByUserId(Long id);
}
