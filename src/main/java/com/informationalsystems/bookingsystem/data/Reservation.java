package com.informationalsystems.bookingsystem.data;

import com.informationalsystems.bookingsystem.reservation.SavedReservationDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_time")
    private Date startTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "customers_amount")
    private Integer customersAmount;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Dish> dishes = new HashSet<>();

    public static SavedReservationDto toSavedReservationDto(Reservation reservation) {
        return SavedReservationDto.builder()
                .id(reservation.getId())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .customersAmount(reservation.getCustomersAmount())
                .customer(Customer.toSavedCustomerDto(reservation.getCustomer()))
                .restaurant(Restaurant.toSavedRestaurantDto(reservation.getRestaurant()))
                .table(RestaurantTable.toSavedRestaurantTableDto(reservation.getTable()))
                .dishes(reservation.getDishes().stream().map(Dish::toSavedDishDto).toList())
                .build();
    }
}
