package com.example.ecommerce.service.employee;


import com.example.ecommerce.enums.AccountStatus;
import com.example.ecommerce.enums.RoleName;
import com.example.ecommerce.model.Employee;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.IEmployeeRepo;
import com.example.ecommerce.repository.IUserRepo;
import com.example.ecommerce.service.role.IRoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepo employeeRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRepo userRepo;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        User user = new User();
        String baseUsername = "nhanvien";
        String username = baseUsername;
        String baseEmail = "nhanvien@gmail.com";
        int i = 1;

        while (userRepo.existsByUsername(username)) {
            username = baseUsername + i;
            i++;
        }

        user.setUsername(username);
        user.setPassword("123456@Abc");
        user.setEmail(baseEmail);
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role defaultRole = roleService.findByName(RoleName.ROLE_EMPLOYEE);
        roles.add(defaultRole);
        user.setRoles(roles);
        userRepo.save(user);

        employee.setUser(user);
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }


    @Transactional
    @Override
    public void deleteEmployeeByUserId(Long id) {
        employeeRepository.deleteByUserId(id);
    }
}
