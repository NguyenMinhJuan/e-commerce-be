package com.example.ecommerce.controller;

import com.example.ecommerce.model.Employee;
import com.example.ecommerce.service.employee.IEmployeeService;
import com.example.ecommerce.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IUserService userService;

    @PostMapping("/employees")
    public ResponseEntity<?> createNewEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees(){
        Iterable<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeByUserId(id);
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
