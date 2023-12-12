package com.informationalsystems.bookingsystem.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SavedDishWithCountDto {

    private SavedDishDto dto;

    private Integer count;

}
