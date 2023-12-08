package com.informationalsystems.bookingsystem.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RestaurantDto {

    @NonNull
    private String name;

    @NonNull
    private String address;

    private List<Date> startTimes;

    private List<Date> endTimes;

}
