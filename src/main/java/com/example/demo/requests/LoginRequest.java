package com.example.demo.requests;

import lombok.*;

@Setter
@Getter
public class LoginRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;

    public LoginRequest(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequest() {
    }
}
