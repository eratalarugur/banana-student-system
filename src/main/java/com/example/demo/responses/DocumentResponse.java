package com.example.demo.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentResponse {

    private int id;

    private String name;

    public DocumentResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DocumentResponse() {
    }
}
