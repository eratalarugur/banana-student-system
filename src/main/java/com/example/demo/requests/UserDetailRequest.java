package com.example.demo.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * The type User detail request.
 */
@Getter
@Setter
public class UserDetailRequest {

    @NonNull
    private String email;

    private int isTeacher;

    /**
     * Instantiates a new User detail request.
     */
    public UserDetailRequest() {
    }

    /**
     * Instantiates a new User detail request.
     *
     * @param email     the email
     * @param isTeacher the is teacher
     */
    public UserDetailRequest(@NonNull String email, int isTeacher) {
        this.email = email;
        this.isTeacher = isTeacher;
    }
}
