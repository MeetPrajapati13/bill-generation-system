package com.example.bill_generation_system.services;

import com.example.bill_generation_system.entity.Product;
import com.example.bill_generation_system.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StockReportService {

    @Autowired
    ProductRepository productRepository;


    public void generateStockReport(){
        Date date = new Date(System.currentTimeMillis());

        SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");

        String format = sm.format(date);

        try(Writer writer = new FileWriter("C:\\Users\\Meet\\Documents\\stock-report("+format+").csv");
            ICsvBeanWriter iCsvBeanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE)){

            String[] headers = {"ProductId","ProductName","ProductStock"};

            iCsvBeanWriter.writeHeader(headers);

            List<Product> products = productRepository.findAll();

            for(Product product : products){
                iCsvBeanWriter.write(product, headers);
            }

        }catch (IOException e){
            e.getMessage();
        }
    }
}
