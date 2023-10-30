package com.informationalsystems.bookingsystem.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class RestaurantDishDto {

    @NonNull
    private String name;

    @NonNull
    private Long price;

}
