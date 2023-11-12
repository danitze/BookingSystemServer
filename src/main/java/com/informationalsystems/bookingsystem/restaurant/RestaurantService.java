package com.informationalsystems.bookingsystem.restaurant;

import com.informationalsystems.bookingsystem.data.Dish;
import com.informationalsystems.bookingsystem.data.Restaurant;
import com.informationalsystems.bookingsystem.data.RestaurantTable;
import com.informationalsystems.bookingsystem.dish.SavedDishDto;
import com.informationalsystems.bookingsystem.repository.RestaurantRepository;
import com.informationalsystems.bookingsystem.table.SavedRestaurantTableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

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
        return mapToSavedRestaurantDtos(restaurants);
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
