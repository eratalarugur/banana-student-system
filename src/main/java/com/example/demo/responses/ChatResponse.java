package com.example.demo.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Chat response.
 */
@Getter
@Setter
public class ChatResponse {

    private String senderName;

    private String date;

    private String message;

    /**
     * Instantiates a new Chat response.
     */
    public ChatResponse() {
    }

    /**
     * Instantiates a new Chat response.
     *
     * @param senderName the sender name
     * @param date       the date
     * @param message    the message
     */
    public ChatResponse(String senderName, String date, String message) {
        this.senderName = senderName;
        this.date = date;
        this.message = message;
    }
}
