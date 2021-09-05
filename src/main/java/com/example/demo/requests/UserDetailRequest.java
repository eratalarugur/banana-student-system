package com.example.demo.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDetailRequest {

    @NonNull
    private String email;

    private int isTeacher;

    public UserDetailRequest() {
    }

    public UserDetailRequest(@NonNull String email, int isTeacher) {
        this.email = email;
        this.isTeacher = isTeacher;
    }
}
