package com.example.demo.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Document response.
 */
@Getter
@Setter
public class DocumentResponse {

    private int id;

    private String name;

    /**
     * Instantiates a new Document response.
     *
     * @param id   the id
     * @param name the name
     */
    public DocumentResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Instantiates a new Document response.
     */
    public DocumentResponse() {
    }
}
