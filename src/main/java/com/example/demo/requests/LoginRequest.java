package com.example.demo.requests;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;
}
