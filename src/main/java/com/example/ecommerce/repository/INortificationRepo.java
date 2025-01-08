package com.example.ecommerce.repository;

import com.example.ecommerce.model.Notification;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INortificationRepo extends JpaRepository<Notification, Long> {
    Iterable<Notification> findAllByUser(User user);
}
