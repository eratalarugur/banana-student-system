package com.example.demo.requests;

import lombok.*;

@Setter
@Getter
public class LoginRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;

    private boolean isTeacher;

    public LoginRequest(@NonNull String email, @NonNull String password, boolean isTeacher) {
        this.email = email;
        this.password = password;
        this.isTeacher = isTeacher;
    }

    public LoginRequest() {
    }
}
