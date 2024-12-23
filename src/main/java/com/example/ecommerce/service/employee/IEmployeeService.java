package com.example.ecommerce.service.employee;

import com.example.ecommerce.model.Employee;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.IGenericService;

import java.util.Optional;

public interface IEmployeeService extends IGenericService<Employee, Long> {
    void deleteEmployeeByUserId(Long id);
    void registerEmployee(Employee employee);
}
