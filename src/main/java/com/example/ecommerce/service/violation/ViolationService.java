package com.example.ecommerce.service.violation;

import com.example.ecommerce.model.Violation;
import com.example.ecommerce.repository.IViolationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ViolationService implements IViolationService {

    @Autowired
    private IViolationRepo violationRepo;

    @Override
    public Iterable<Violation> findAll() {
        return violationRepo.findAll();
    }

    @Override
    public Optional<Violation> findById(Long id) {
        return violationRepo.findById(id);
    }

    @Override
    public void save(Violation violation) {
        violationRepo.save(violation);
    }

    @Override
    public void delete(Long id) {
        violationRepo.deleteById(id);
    }
}
