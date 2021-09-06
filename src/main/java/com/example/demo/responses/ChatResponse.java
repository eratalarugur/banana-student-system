package com.example.demo.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ChatResponse {

    private String senderName;

    private String date;

    private String message;

    public ChatResponse() {
    }

    public ChatResponse(String senderName, String date, String message) {
        this.senderName = senderName;
        this.date = date;
        this.message = message;
    }
}
