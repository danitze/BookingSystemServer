package com.informationalsystems.bookingsystem.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class RestaurantRegisterRequest {

    @NonNull
    private String phoneNumber;

    @NonNull
    private String password;

    @NonNull
    private String name;

    @NonNull
    private String address;

}
