package com.informationalsystems.bookingsystem.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SavedCustomerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

}
