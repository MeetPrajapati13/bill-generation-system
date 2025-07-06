package com.example.bill_generation_system.services;

import com.example.bill_generation_system.configuration.AdminConfig;
import com.example.bill_generation_system.configuration.EmailConfig;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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

            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
            String format = sm.format(date);

            String fileName = "stock-report("+format+").csv";

            FileSystemResource fileSystemResource = new FileSystemResource(new File("C:\\Users\\Meet\\Documents\\" + fileName));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);

            javaMailSender.send(message);

        }catch (Exception e){
            e.getMessage();
        }

    }
}
