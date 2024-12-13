package com.example.ecommerce.controller;

import com.example.ecommerce.model.Violation;
import com.example.ecommerce.service.violation.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/violations")
public class ViolationController {

    @Autowired
    private ViolationService violationService;

    @PostMapping()
    public ResponseEntity<String> saveViolation(@RequestBody Violation violation) {
        violationService.save(violation);
        System.out.println("Violation saved: " + violation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Violation saved successfully!");
    }
}
