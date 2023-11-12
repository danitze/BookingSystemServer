package com.informationalsystems.bookingsystem.dish;

import com.informationalsystems.bookingsystem.data.Dish;
import com.informationalsystems.bookingsystem.data.Restaurant;
import com.informationalsystems.bookingsystem.data.User;
import com.informationalsystems.bookingsystem.repository.DishRepository;
import com.informationalsystems.bookingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final UserRepository userRepository;

    @Transactional
    public SavedDishDto addDish(Principal principal, DishDto dto) {
        Restaurant restaurant = userRepository.findByPhoneNumber(principal.getName())
                .map(User::getRestaurant)
                .orElseThrow();
        Dish dish = Dish.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
        restaurant.getDishes().add(dish);
        dish.setRestaurant(restaurant);
        dishRepository.save(dish);
        return Dish.toSavedDishDto(dish);
    }

}
