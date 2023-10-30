package com.informationalsystems.bookingsystem.dish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class DishRegisterDto {

    @NonNull
    private String name;

    @NonNull
    private Long price;

}
