package com.example.demo.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ChatRequest {

    private String message;

    private Long courseId;

    private Long studentId;

    public ChatRequest() {
    }

    public ChatRequest(String message, Long courseId, Long studentId) {
        this.message = message;
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
