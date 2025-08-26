package com.example.artemis;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;



public record Letter (

    @JsonProperty("title")
    String title,

    @JsonProperty("sender")
    String sender,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("sentOn")
    LocalDate sentOn,
    @JsonProperty("content")
    String content
) {

}
