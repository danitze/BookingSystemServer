package com.informationalsystems.bookingsystem.restaurant;

import com.informationalsystems.bookingsystem.dish.SavedDishDto;
import com.informationalsystems.bookingsystem.table.SavedRestaurantTableDto;
import com.informationalsystems.bookingsystem.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    @PutMapping
    public ResponseEntity<?> update(
            Authentication authentication,
            Principal principal,
            @RequestBody RestaurantDto dto
    ) {
        if (!AuthenticationUtil.isRestaurant(authentication)) {
            throw new AccessDeniedException("Need to be restaurant");
        }
        return ResponseEntity.ok(service.update(principal, dto));
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<?> read(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.read(id));
    }

    @GetMapping("/tables-with-incomes")
    public ResponseEntity<?> getTablesWithIncomes(
            Authentication authentication,
            Principal principal
    ) {
        if (!AuthenticationUtil.isRestaurant(authentication)) {
            throw new AccessDeniedException("Need to be restaurant");
        }
        return ResponseEntity.ok(service.getTablesWithIncomes(principal));
    }

}
