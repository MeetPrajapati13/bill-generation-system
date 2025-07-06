package com.example.bill_generation_system;

import com.example.bill_generation_system.services.StockReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class BillGenerationSystemApplication implements CommandLineRunner {
	@Autowired
	StockReportService service;

	public static void main(String[] args) {
		SpringApplication.run(BillGenerationSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.generateStockReport();
	}

}
