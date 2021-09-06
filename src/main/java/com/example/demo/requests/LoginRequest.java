package com.example.demo.requests;

import lombok.*;

/**
 * The type Login request.
 */
@Setter
@Getter
public class LoginRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;

    private int isTeacher;

    /**
     * Instantiates a new Login request.
     *
     * @param email     the email
     * @param password  the password
     * @param isTeacher the is teacher
     */
    public LoginRequest(@NonNull String email, @NonNull String password, int isTeacher) {
        this.email = email;
        this.password = password;
        this.isTeacher = isTeacher;
    }

    /**
     * Instantiates a new Login request.
     */
    public LoginRequest() {
    }

}
