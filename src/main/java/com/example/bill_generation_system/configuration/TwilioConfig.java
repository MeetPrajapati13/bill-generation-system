package com.example.bill_generation_system.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig{

    private String accountSid;

    private String authToken;

    private String phoneNumber;

    private String whatsAppNumber;

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWhatsAppNumber() {
        return whatsAppNumber;
    }

    public void setWhatsAppNumber(String whatsAppNumber) {
        this.whatsAppNumber = whatsAppNumber;
    }
}
