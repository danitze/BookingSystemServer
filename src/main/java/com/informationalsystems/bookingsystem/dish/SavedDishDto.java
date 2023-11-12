package com.informationalsystems.bookingsystem.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SavedDishDto {

    private Long id;

    private String name;

    private Long price;

}
