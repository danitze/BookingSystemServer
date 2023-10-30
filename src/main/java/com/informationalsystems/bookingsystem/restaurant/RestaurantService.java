package com.informationalsystems.bookingsystem.restaurant;

import com.informationalsystems.bookingsystem.data.Dish;
import com.informationalsystems.bookingsystem.data.Restaurant;
import com.informationalsystems.bookingsystem.data.RestaurantTable;
import com.informationalsystems.bookingsystem.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantTablesResultDto getRestaurantTables(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(Restaurant::getTables)
                .map(this::mapToTablesResultDto)
                .orElseThrow();
    }

    public RestaurantDishesResultDto getRestaurantDishes(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(Restaurant::getDishes)
                .map(this::mapToDishesResultDto)
                .orElseThrow();
    }

    public RestaurantsInfoDto getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return mapToRestaurantsInfoDto(restaurants);
    }

    private RestaurantTablesResultDto mapToTablesResultDto(Set<RestaurantTable> tables) {
        List<RestaurantTableDto> tablesList = tables.stream().map(table ->
                RestaurantTableDto
                        .builder()
                        .seats(table.getSeats())
                        .position(table.getPosition())
                        .description(table.getDescription())
                        .build()
        ).toList();
        return new RestaurantTablesResultDto(tablesList);
    }

    private RestaurantDishesResultDto mapToDishesResultDto(Set<Dish> dishes) {
        List<RestaurantDishDto> dishesList = dishes.stream().map(dish ->
                RestaurantDishDto.builder()
                        .name(dish.getName())
                        .price(dish.getPrice())
                        .build()
        ).toList();
        return new RestaurantDishesResultDto(dishesList);
    }

    private RestaurantsInfoDto mapToRestaurantsInfoDto(List<Restaurant> restaurants) {
        List<RestaurantInfoDto> restaurantsList = restaurants.stream().map(restaurant ->
                        RestaurantInfoDto.builder()
                                .phoneNumber(restaurant.getUserEntity().getPhoneNumber())
                                .name(restaurant.getName())
                                .address(restaurant.getAddress())
                                .startTimes(restaurant.getStartTimes())
                                .endTimes(restaurant.getEndTimes())
                                .build()
                )
                .toList();
        return new RestaurantsInfoDto(restaurantsList);
    }

}
