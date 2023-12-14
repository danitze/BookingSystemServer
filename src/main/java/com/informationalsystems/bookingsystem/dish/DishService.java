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
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final UserRepository userRepository;

    @Transactional
    public SavedDishDto create(Principal principal, DishDto dto) {
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

    public SavedDishDto read(Long id) {
        return dishRepository.findById(id).map(Dish::toSavedDishDto).orElseThrow();
    }

    @Transactional
    public SavedDishDto update(Principal principal, Long id, DishDto dto) {
        Restaurant restaurant = userRepository.findByPhoneNumber(principal.getName())
                .map(User::getRestaurant)
                .orElseThrow();
        Dish dish = dishRepository.findById(id).filter(d -> d.getRestaurant() == restaurant).orElseThrow();
        dish.setName(dto.getName());
        dish.setPrice(dto.getPrice());
        return Dish.toSavedDishDto(dish);
    }

    @Transactional
    public String delete(Principal principal, Long id) {
        Restaurant restaurant = userRepository.findByPhoneNumber(principal.getName())
                .map(User::getRestaurant)
                .orElseThrow();
        if (restaurant.getDishes().stream().noneMatch(d -> Objects.equals(d.getId(), id))) {
            throw new NoSuchElementException();
        }
        dishRepository.deleteByPid(id);
        return "Ok";
    }

}
