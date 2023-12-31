package com.informationalsystems.bookingsystem.table;

import com.informationalsystems.bookingsystem.data.RestaurantTablePosition;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class RestaurantTableDto {
    @NonNull
    private Integer seats;

    @NonNull
    private RestaurantTablePosition position;

    private String description;

}
