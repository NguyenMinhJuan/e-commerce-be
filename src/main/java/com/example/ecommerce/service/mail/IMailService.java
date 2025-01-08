package com.example.ecommerce.service.mail;

import com.example.ecommerce.model.dto.DataMailDTO;
import jakarta.mail.MessagingException;

public interface IMailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;

}
