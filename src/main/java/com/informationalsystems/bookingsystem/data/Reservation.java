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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "customers_amount")
    private Integer customersAmount;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReservationDish> reservationDishes = new HashSet<>();

    public static SavedReservationDto toSavedReservationDto(Reservation reservation) {
        return SavedReservationDto.builder()
                .id(reservation.getId())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .customersAmount(reservation.getCustomersAmount())
                .customer(Customer.toSavedCustomerDto(reservation.getCustomer()))
                .restaurant(Restaurant.toSavedRestaurantDto(reservation.getRestaurant()))
                .table(RestaurantTable.toSavedRestaurantTableDto(reservation.getTable()))
                .dishes(reservation.getReservationDishes().stream().map(ReservationDish::toSavedDishWithCountDto).toList())
                .build();
    }
}
