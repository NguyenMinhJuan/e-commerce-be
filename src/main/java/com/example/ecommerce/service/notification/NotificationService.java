package com.example.ecommerce.service.notification;

import com.example.ecommerce.model.Notification;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.INortificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INortificationRepo iNotificationRepo;

    @Override
    public Iterable<Notification> findAll() {
        return iNotificationRepo.findAll();
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return iNotificationRepo.findById(id);
    }

    @Override
    public void save(Notification notification) {
        iNotificationRepo.save(notification);
    }

    @Override
    public void delete(Long id) {
        iNotificationRepo.deleteById(id);
    }

    @Override
    public Iterable<Notification> findAllByUser(User user) {
        return iNotificationRepo.findAllByUser(user);
    }
}
