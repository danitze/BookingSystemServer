package com.informationalsystems.bookingsystem.restaurant;

import com.informationalsystems.bookingsystem.data.RestaurantTablePosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class RestaurantTableDto {

    @NonNull
    private Integer seats;

    @NonNull
    private RestaurantTablePosition position;

    private String description;

}
