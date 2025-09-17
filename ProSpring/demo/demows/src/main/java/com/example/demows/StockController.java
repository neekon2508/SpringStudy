package com.example.demows;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;

import jakarta.annotation.PostConstruct;

@Controller
public class StockController {
    private final TaskScheduler taskScheduler;
    private final SimpMessagingTemplate simpMessagingTemplate;

    private List<Stock> stocks = new ArrayList<>();
    private Random random = new Random(System.currentTimeMillis());

    public StockController(TaskScheduler taskScheduler, SimpMessagingTemplate simpMessagingTemplate) {
        this.taskScheduler = taskScheduler;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/addStock")
    public void addStock(Stock stock) throws Exception {
        stocks.add(stock);
        
    }

    private void broadcastUpdatedPrices() {
        for(Stock stock : stocks) {
            stock.setPrice(stock.getPrice() + (getUpdatedStockPrice() * stock.getPrice()));
            stock.setDate(LocalDateTime.now());
        }
        simpMessagingTemplate.convertAndSend("/topic/price", stocks);
    }

    private double getUpdatedStockPrice() {
        double priceChange = random.nextDouble() * 5.0;
        if (random.nextInt(2)==1)
                priceChange = -priceChange;
        return priceChange / 100.0;
    }

    @PostConstruct
    private void boradcastTimePeriodically() {
        stocks.add(new Stock("VMW", 1.00d));
        stocks.add(new Stock("EMC", 1.00d));
        stocks.add(new Stock("GOOG", 1.00d));
        stocks.add(new Stock("IBM", 1.00d));

        taskScheduler.scheduleAtFixedRate(this::broadcastUpdatedPrices, Duration.ofSeconds(2));
    }
}
