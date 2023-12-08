package com.informationalsystems.bookingsystem.reservation;

import com.informationalsystems.bookingsystem.data.*;
import com.informationalsystems.bookingsystem.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Transactional
    public SavedReservationDto update(Principal principal, Long reservationId, ReservationDto dto) {
        Customer customer = userRepository.findByPhoneNumber(principal.getName()).map(User::getCustomer).orElseThrow();
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).orElseThrow();
        RestaurantTable table = tableRepository.findById(dto.getTableId()).orElseThrow();
        Set<Dish> dishes = Optional.ofNullable(dto.getDishIds()).orElse(List.of()).stream()
                .map(id -> dishRepository.findById(id).orElseThrow())
                .collect(Collectors.toSet());
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        customer.getReservations().remove(reservation);
        restaurant.getReservations().remove(reservation);
        dishes.forEach(dish -> dish.getReservations().remove(reservation));
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setCustomersAmount(dto.getCustomersAmount());
        reservation.setCustomer(customer);
        reservation.setRestaurant(restaurant);
        reservation.setTable(table);
        reservation.setDishes(dishes);
        customer.getReservations().add(reservation);
        restaurant.getReservations().add(reservation);
        dishes.forEach(dish -> dish.getReservations().add(reservation));
        return Reservation.toSavedReservationDto(reservation);
    }

    @Transactional
    public String delete(Principal principal, Long reservationId) {
        User user = userRepository.findByPhoneNumber(principal.getName()).orElseThrow();
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        boolean isReservationPresent = switch (user.getRole()) {
            case CUSTOMER -> user.getCustomer().getReservations().contains(reservation);
            case RESTAURANT -> user.getRestaurant().getReservations().contains(reservation);
        };
        if (!isReservationPresent) {
            throw new NoSuchElementException();
        }
        reservationRepository.deleteById(reservationId);
        return "Ok";
    }

    public List<SavedReservationDto> getAll(Principal principal) {
        User user = userRepository.findByPhoneNumber(principal.getName()).orElseThrow();
        return switch (user.getRole()) {
            case CUSTOMER -> user.getCustomer().getReservations().stream().map(Reservation::toSavedReservationDto).toList();
            case RESTAURANT -> user.getRestaurant().getReservations().stream().map(Reservation::toSavedReservationDto).toList();
        };
    }

}
