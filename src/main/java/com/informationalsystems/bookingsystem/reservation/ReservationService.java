package com.informationalsystems.bookingsystem.reservation;

import com.informationalsystems.bookingsystem.data.*;
import com.informationalsystems.bookingsystem.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final DishRepository dishRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public SavedReservationDto create(Principal principal, ReservationDto dto) {
        Customer customer = userRepository.findByPhoneNumber(principal.getName()).map(User::getCustomer).orElseThrow();
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).orElseThrow();
        RestaurantTable table = tableRepository.findById(dto.getTableId()).orElseThrow();
        Set<Dish> dishes = Optional.ofNullable(dto.getDishIds()).orElse(List.of()).stream()
                .map(id -> dishRepository.findById(id).orElseThrow())
                .collect(Collectors.toSet());
        Reservation reservation = Reservation.builder()
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .customersAmount(dto.getCustomersAmount())
                .customer(customer)
                .restaurant(restaurant)
                .table(table)
                .dishes(dishes)
                .build();
        customer.getReservations().add(reservation);
        restaurant.getReservations().add(reservation);
        table.getReservations().add(reservation);
        dishes.forEach(dish -> dish.getReservations().add(reservation));
        reservationRepository.save(reservation);
        return Reservation.toSavedReservationDto(reservation);
    }

}
