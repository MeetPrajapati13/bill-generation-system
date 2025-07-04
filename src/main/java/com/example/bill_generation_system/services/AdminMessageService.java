package com.example.bill_generation_system.services;

import com.example.bill_generation_system.configuration.AdminConfig;
import com.example.bill_generation_system.configuration.EmailConfig;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class AdminMessageService {
    @Autowired
    AdminConfig adminConfig;

    @Autowired
    EmailConfig emailConfig;

    @Autowired
    JavaMailSender javaMailSender;


    public void sendStockReport(){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

            mimeMessageHelper.setFrom(emailConfig.getUserName());
            mimeMessageHelper.setTo(adminConfig.getEmail());
            mimeMessageHelper.setText("Stock Report of Products");
            mimeMessageHelper.setSubject("Stock Report");


        }catch (Exception e){
            e.getMessage();
        }

    }
}
