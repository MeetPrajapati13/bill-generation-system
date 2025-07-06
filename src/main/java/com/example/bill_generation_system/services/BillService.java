package com.example.bill_generation_system.services;

import com.example.bill_generation_system.dto.response.OrderResponseDTO;
import com.example.bill_generation_system.entity.Bill;
import com.example.bill_generation_system.entity.Customer;
import com.example.bill_generation_system.entity.Order;
import com.example.bill_generation_system.entity.Product;
import com.example.bill_generation_system.notification.SMS;
import com.example.bill_generation_system.notification.WhatsApp;
import com.example.bill_generation_system.repository.BillRepository;
import com.example.bill_generation_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class BillService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    SMS sms;

    @Autowired
    WhatsApp whatsApp;

    @Autowired
    StockReportService stockReportService;

    public OrderResponseDTO generateBill(Customer customer,Product product, double quantity){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        double baseAmount = product.getProductPrice() * quantity;
        double gstAmount = baseAmount * product.getGstRate() / 100;
        double totalAmount = baseAmount + gstAmount;

        if(!paymentStatus()){
            sms.onPaymentFailedSms(customer.getCustomerName(), product.getProductName(), totalAmount);
            whatsApp.onPaymentFailed(customer.getCustomerName(), product.getProductName(), totalAmount);

            orderResponseDTO.setMessage("Order Payment Failed");
            orderResponseDTO.setHttpStatus(HttpStatus.PAYMENT_REQUIRED);

            return orderResponseDTO;
        }
        
        product.setProductStock(product.getProductStock() - quantity);

        if(product.getProductStock() <= product.getThresholdQuantity()) stockReportService.generateStockReport();

        Order order = saveOrderDetail(customer, product ,quantity);

        Bill bill = new Bill();
        bill.setBillDate(LocalDate.now());
        bill.setGstAmount(gstAmount);
        bill.setTotalAmount(totalAmount);
        bill.setOrder(order);

        billRepository.save(bill);

        orderResponseDTO.setMessage("Order Placed SuccessFully");
        orderResponseDTO.setHttpStatus(HttpStatus.OK);

        sms.onPaymentSuccessSms(customer.getCustomerName(), product.getProductName(), totalAmount);
        whatsApp.onPaymentSuccess(customer.getCustomerName(), product.getProductName(), totalAmount);

        return orderResponseDTO;
    }

    public Order saveOrderDetail(Customer customer, Product product, double quantity){
        Order order = new Order();

        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(quantity);

        orderRepository.save(order);

        return order;
    }

    public boolean paymentStatus(){
        return new Random().nextBoolean();
    }

}

