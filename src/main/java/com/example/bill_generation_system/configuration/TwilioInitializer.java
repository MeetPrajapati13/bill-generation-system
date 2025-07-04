package com.example.bill_generation_system.configuration;

import com.example.bill_generation_system.services.StockReportService;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {

    @Autowired
    TwilioConfig twilioConfig;

    @Autowired
    StockReportService service;

    @PostConstruct
    public void initTwilio(){
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }
}
