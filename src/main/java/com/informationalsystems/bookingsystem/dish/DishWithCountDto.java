package com.informationalsystems.bookingsystem.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class DishWithCountDto {

    @NonNull
    private Long dishId;

    @NonNull
    private Integer count;

}
