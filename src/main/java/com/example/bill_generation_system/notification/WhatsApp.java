package com.example.bill_generation_system.notification;

import com.example.bill_generation_system.configuration.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WhatsApp {
    @Autowired
    TwilioConfig twilioConfig;

    public void onPaymentSuccess(String customerName, String productName, double amount){
        String messageBody = "✅ *Payment Successful!*\n\n" +
                "👤 *Dear :* " + customerName + "\n" +
                "📦 *Product :* " + productName + "\n" +
                "💰 *Amount Paid:* " + amount + "\n\n" +
                "🧾 Thank you for your purchase! We appreciate your business.";

        Message message = Message.creator(
                new PhoneNumber("whatsapp:+919825949566"),
                new PhoneNumber(twilioConfig.getWhatsAppNumber()),
                messageBody
        ).create();

    }

    public void onPaymentFailed(String customerName, String productName, double amount){
        String messageBody = "❌ *Payment Failed!*\n\n" +
                "👤 Dear : " + customerName + "\n" +
                "📦 Product : " + productName + "\n" +
                "💰 Amount : " + amount + "\n\n" +
                "⚠️ We couldn't process your payment. ";

        Message message = Message.creator(
                new PhoneNumber("whatsapp:+919825949566"),
                new PhoneNumber(twilioConfig.getWhatsAppNumber()),
                messageBody
        ).create();

    }
}
