package com.informationalsystems.bookingsystem.restaurant;

import com.informationalsystems.bookingsystem.data.*;
import com.informationalsystems.bookingsystem.dish.SavedDishDto;
import com.informationalsystems.bookingsystem.repository.RestaurantRepository;
import com.informationalsystems.bookingsystem.repository.UserRepository;
import com.informationalsystems.bookingsystem.table.SavedRestaurantTableDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public SavedRestaurantDto update(
            Principal principal,
            RestaurantDto dto
    ) {
        Restaurant restaurant = userRepository.findByPhoneNumber(principal.getName()).map(User::getRestaurant).orElseThrow();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setStartTimes(dto.getStartTimes());
        restaurant.setEndTimes(dto.getEndTimes());
        return Restaurant.toSavedRestaurantDto(restaurant);
    }

    public List<SavedRestaurantTableDto> getRestaurantTables(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(Restaurant::getTables)
                .map(this::mapToSavedRestaurantTableDtos)
                .orElseThrow();
    }

    public List<SavedDishDto> getRestaurantDishes(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(Restaurant::getDishes)
                .map(this::mapToSavedDishDtos)
                .orElseThrow();
    }

    public List<SavedRestaurantDto> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        System.out.println("MyTag restaurants got!!!");
        return mapToSavedRestaurantDtos(restaurants);
    }

    public SavedRestaurantDto read(Long id) {
        return restaurantRepository.findById(id).map(Restaurant::toSavedRestaurantDto).orElseThrow();
    }

    public List<TableWithIncomeDto> getTablesWithIncomes(Principal principal) {
        Restaurant restaurant = userRepository.findByPhoneNumber(principal.getName()).map(User::getRestaurant).orElseThrow();
        return restaurant.getTables().stream().map(table -> {
            Long income = table.getReservations().stream()
                    .map(Reservation::getReservationDishes)
                    .map(reservationDishes ->
                            reservationDishes.stream()
                                    .map(reservationDish -> reservationDish.getCount() * reservationDish.getDish().getPrice())
                                    .reduce(0L, Long::sum))
                    .reduce(0L, Long::sum);
            return TableWithIncomeDto.builder()
                    .dto(RestaurantTable.toSavedRestaurantTableDto(table))
                    .income(income)
                    .build();
        }).collect(Collectors.toList());
    }

    private List<SavedRestaurantTableDto> mapToSavedRestaurantTableDtos(Set<RestaurantTable> tables) {
        return tables.stream().map(RestaurantTable::toSavedRestaurantTableDto).toList();
    }

    private List<SavedDishDto> mapToSavedDishDtos(Set<Dish> dishes) {
        return dishes.stream().map(Dish::toSavedDishDto).toList();
    }

    private List<SavedRestaurantDto> mapToSavedRestaurantDtos(List<Restaurant> restaurants) {
        return restaurants.stream().map(Restaurant::toSavedRestaurantDto).toList();
    }

}
