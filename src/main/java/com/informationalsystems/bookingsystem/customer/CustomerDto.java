package com.informationalsystems.bookingsystem.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {
    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
}
