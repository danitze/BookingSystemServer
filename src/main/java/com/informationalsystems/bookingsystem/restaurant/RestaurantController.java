package com.informationalsystems.bookingsystem.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    @GetMapping("/tables")
    public ResponseEntity<RestaurantTablesResultDto> getTables(
            @RequestParam(name = "restaurant_id") Long restaurantId
    ) {
        return ResponseEntity.ok(service.getRestaurantTables(restaurantId));
    }

    @GetMapping("/dishes")
    public ResponseEntity<RestaurantDishesResultDto> getDishes(
            @RequestParam(name = "restaurant_id") Long restaurantId
    ) {
        return ResponseEntity.ok(service.getRestaurantDishes(restaurantId));
    }

    @GetMapping("/list")
    public ResponseEntity<RestaurantsInfoDto> getRestaurants() {
        return ResponseEntity.ok(service.getRestaurants());
    }

}
