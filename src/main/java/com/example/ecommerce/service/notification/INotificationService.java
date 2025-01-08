package com.example.ecommerce.service.notification;

import com.example.ecommerce.model.Notification;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.IGenericService;

public interface INotificationService extends IGenericService<Notification,Long> {
    Iterable<Notification> findAllByUser(User user);
}
