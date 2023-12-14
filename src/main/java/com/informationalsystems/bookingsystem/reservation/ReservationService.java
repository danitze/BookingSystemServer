package com.informationalsystems.bookingsystem.reservation;

import com.informationalsystems.bookingsystem.data.*;
import com.informationalsystems.bookingsystem.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
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
        if (dto.getCustomersAmount() > table.getSeats()) {
            throw new IllegalStateException("Customers amount more than seats");
        }
        table.getReservations().forEach(reservation -> {
            Date startTime = reservation.getStartTime();
            Date endTime = reservation.getEndTime();
            if ((dto.getStartTime().after(startTime) && dto.getStartTime().before(endTime)) || (dto.getEndTime().after(startTime) && dto.getEndTime().before(endTime))) {
                throw new IllegalStateException("Time is already booked");
            }
        });
        Reservation reservation = Reservation.builder()
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .customersAmount(dto.getCustomersAmount())
                .customer(customer)
                .restaurant(restaurant)
                .table(table)
                .build();
        Set<ReservationDish> reservationDishes = Optional.ofNullable(dto.getDishesWithCount()).orElse(List.of())
                .stream()
                .map(dishWithCount -> ReservationDish.builder()
                        .dish(dishRepository.findById(dishWithCount.getDishId()).orElseThrow())
                        .reservation(reservation)
                        .count(dishWithCount.getCount())
                        .build())
                .collect(Collectors.toSet());
        reservation.setReservationDishes(reservationDishes);
        reservationDishes.forEach(reservationDish -> reservationDish.getDish().getReservationDishes().add(reservationDish));
        customer.getReservations().add(reservation);
        restaurant.getReservations().add(reservation);
        table.getReservations().add(reservation);
        reservationRepository.save(reservation);
        return Reservation.toSavedReservationDto(reservation);
    }

    @Transactional
    public SavedReservationDto update(Principal principal, Long reservationId, ReservationDto dto) {
        Customer customer = userRepository.findByPhoneNumber(principal.getName()).map(User::getCustomer).orElseThrow();
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).orElseThrow();
        RestaurantTable table = tableRepository.findById(dto.getTableId()).orElseThrow();
        if (dto.getCustomersAmount() > table.getSeats()) {
            throw new IllegalStateException("Customers amount more than seats");
        }
        table.getReservations().forEach(reservation -> {
            Date startTime = reservation.getStartTime();
            Date endTime = reservation.getEndTime();
            if ((dto.getStartTime().after(startTime) && dto.getStartTime().before(endTime)) || (dto.getEndTime().after(startTime) && dto.getEndTime().before(endTime))) {
                throw new IllegalStateException("Time is already booked");
            }
        });
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        customer.getReservations().remove(reservation);
        restaurant.getReservations().remove(reservation);
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setCustomersAmount(dto.getCustomersAmount());
        reservation.setCustomer(customer);
        reservation.setRestaurant(restaurant);
        reservation.setTable(table);
        customer.getReservations().add(reservation);
        restaurant.getReservations().add(reservation);
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
        reservationRepository.deleteByPid(reservationId);
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
