package com.informationalsystems.bookingsystem.reservation;

import com.informationalsystems.bookingsystem.customer.SavedCustomerDto;
import com.informationalsystems.bookingsystem.dish.SavedDishDto;
import com.informationalsystems.bookingsystem.restaurant.SavedRestaurantDto;
import com.informationalsystems.bookingsystem.table.SavedRestaurantTableDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SavedReservationDto {

    private Long id;

    private Date startTime;

    private Date endTime;

    private Integer customersAmount;

    private SavedCustomerDto customer;

    private SavedRestaurantDto restaurant;

    private SavedRestaurantTableDto table;

    private List<SavedDishDto> dishes;

}
