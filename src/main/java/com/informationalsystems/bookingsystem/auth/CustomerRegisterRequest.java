package com.informationalsystems.bookingsystem.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class CustomerRegisterRequest {

    @NonNull
    private String phoneNumber;

    @NonNull
    private String password;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

}
