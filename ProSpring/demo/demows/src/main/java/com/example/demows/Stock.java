package com.example.demows;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class Stock implements Serializable{
    private static final long serialVersionUID = 1L;

    private static final String DATE_FORMAT = "MMM dd yyyy HH:mm:ss";

    private String code;
    private double price;

    private LocalDateTime date;

    public Stock() { }

    public Stock(String code, double price) {
        this.code = code;
        this.price = price;
    }

    public String getDateFormatted() {
        var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return formatter.format(date);
    }
}
