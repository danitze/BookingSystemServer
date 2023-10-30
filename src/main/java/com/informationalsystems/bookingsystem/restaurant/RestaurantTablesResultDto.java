package com.informationalsystems.bookingsystem.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RestaurantTablesResultDto {

    private List<RestaurantTableDto> tables;

}
