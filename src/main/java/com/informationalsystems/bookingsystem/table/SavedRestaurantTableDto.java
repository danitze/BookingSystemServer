package com.informationalsystems.bookingsystem.table;

import com.informationalsystems.bookingsystem.data.RestaurantTablePosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SavedRestaurantTableDto {

    private Long id;

    private Integer seats;

    private RestaurantTablePosition position;

    private String description;

}
