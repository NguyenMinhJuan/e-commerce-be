package com.example.ecommerce.controller;

import com.example.ecommerce.model.User;
import com.example.ecommerce.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            if (userService.existsByUsername(user.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
            userService.registerUser(user);
            return ResponseEntity.ok().body("Successfully signed up");
        } catch (Exception e) {
            return ResponseEntity.ok().body("Something went wrong!");
        }
    }

    @PutMapping("/setStatus/{username}")
    public ResponseEntity<?> setStatus(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            userService.setAccountStatus(user);
            return ResponseEntity.ok().body("Successfully updated status");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update status");
        }
    }

    @PostMapping("/checkExistUsername/{username}")
    public ResponseEntity<?> checkExistUsername(@PathVariable String username) {
        try {
            userService.existsByUsername(username);
            return ResponseEntity.badRequest().body("Username already exists");
        } catch (Exception e) {
            return ResponseEntity.ok().body("Successfully checked username");
        }
    }
}
