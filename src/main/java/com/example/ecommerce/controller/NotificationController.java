package com.example.ecommerce.controller;

import com.example.ecommerce.model.Notification;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.notification.NotificationService;
import com.example.ecommerce.service.user.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllNotificationsByUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        Iterable<Notification> notifications = notificationService.findAllByUser(user);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
