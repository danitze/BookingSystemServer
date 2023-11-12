package com.informationalsystems.bookingsystem.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SavedRestaurantDto {

    private Long id;

    private String phoneNumber;

    private String name;

    private String address;

    private List<Date> startTimes;

    private List<Date> endTimes;

}
