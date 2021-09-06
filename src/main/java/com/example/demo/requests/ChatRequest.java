package com.example.demo.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * The type Chat request.
 */
@Getter
@Setter
@ToString
public class ChatRequest {

    private String message;

    private Long courseId;

    private Long studentId;

    /**
     * Instantiates a new Chat request.
     */
    public ChatRequest() {
    }

    /**
     * Instantiates a new Chat request.
     *
     * @param message   the message
     * @param courseId  the course id
     * @param studentId the student id
     */
    public ChatRequest(String message, Long courseId, Long studentId) {
        this.message = message;
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
