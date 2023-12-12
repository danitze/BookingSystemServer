package com.informationalsystems.bookingsystem.reservation;

import com.informationalsystems.bookingsystem.dish.DishWithCountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ReservationDto {

    @NonNull
    private Date startTime;

    @NonNull
    private Date endTime;

    @NonNull
    private Integer customersAmount;

    @NonNull
    private Long restaurantId;

    @NonNull
    private Long tableId;

    private List<DishWithCountDto> dishesWithCount;

}
