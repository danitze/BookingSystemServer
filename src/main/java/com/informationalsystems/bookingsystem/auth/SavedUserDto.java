package com.informationalsystems.bookingsystem.auth;

import com.informationalsystems.bookingsystem.customer.SavedCustomerDto;
import com.informationalsystems.bookingsystem.data.Role;
import com.informationalsystems.bookingsystem.restaurant.SavedRestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SavedUserDto {

    private Long id;

    private Role role;

    private SavedCustomerDto customer;

    private SavedRestaurantDto restaurant;

}
