package com.informationalsystems.bookingsystem.restaurant;

import com.informationalsystems.bookingsystem.dish.SavedDishDto;
import com.informationalsystems.bookingsystem.table.SavedRestaurantTableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    @GetMapping("/tables")
    public ResponseEntity<List<SavedRestaurantTableDto>> getTables(
            @RequestParam(name = "restaurant_id") Long restaurantId
    ) {
        return ResponseEntity.ok(service.getRestaurantTables(restaurantId));
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<SavedDishDto>> getDishes(
            @RequestParam(name = "restaurant_id") Long restaurantId
    ) {
        return ResponseEntity.ok(service.getRestaurantDishes(restaurantId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<SavedRestaurantDto>> getRestaurants() {
        return ResponseEntity.ok(service.getRestaurants());
    }

}
