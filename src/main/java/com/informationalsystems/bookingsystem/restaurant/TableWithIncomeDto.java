package com.informationalsystems.bookingsystem.restaurant;

import com.informationalsystems.bookingsystem.table.SavedRestaurantTableDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TableWithIncomeDto {

    private SavedRestaurantTableDto dto;

    private Long income;

}
