package com.informationalsystems.bookingsystem.table;

import com.informationalsystems.bookingsystem.data.Restaurant;
import com.informationalsystems.bookingsystem.data.RestaurantTable;
import com.informationalsystems.bookingsystem.data.User;
import com.informationalsystems.bookingsystem.repository.TableRepository;
import com.informationalsystems.bookingsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TableService {

    private final UserRepository userRepository;

    private final TableRepository tableRepository;

    @Transactional
    public SavedRestaurantTableDto addTable(Principal principal, RestaurantTableDto dto) {
        Restaurant restaurant = userRepository
                .findByPhoneNumber(principal.getName())
                .map(User::getRestaurant)
                .orElseThrow();
        RestaurantTable table = RestaurantTable.builder()
                .seats(dto.getSeats())
                .position(dto.getPosition())
                .description(dto.getDescription())
                .build();
        table.setRestaurant(restaurant);
        restaurant.getTables().add(table);
        tableRepository.save(table);
        return RestaurantTable.toSavedRestaurantTableDto(table);
    }

    @Transactional
    public SavedRestaurantTableDto update(Principal principal, Long id, RestaurantTableDto dto) {
        Restaurant restaurant = userRepository
                .findByPhoneNumber(principal.getName())
                .map(User::getRestaurant)
                .orElseThrow();
        RestaurantTable table = tableRepository.findById(id).orElseThrow();
        if (!restaurant.getTables().contains(table)) {
            throw new NoSuchElementException();
        }
        table.setSeats(dto.getSeats());
        table.setPosition(dto.getPosition());
        table.setDescription(dto.getDescription());
        return RestaurantTable.toSavedRestaurantTableDto(table);
    }

    @Transactional
    public String delete(Principal principal, Long id) {
        Restaurant restaurant = userRepository
                .findByPhoneNumber(principal.getName())
                .map(User::getRestaurant)
                .orElseThrow();
        if (restaurant.getTables().stream().noneMatch(t -> Objects.equals(t.getId(), id))) {
            throw new NoSuchElementException();
        }
        tableRepository.deleteByPid(id);
        return "Ok";
    }

    public SavedRestaurantTableDto read(Long id) {
        return tableRepository.findById(id).map(RestaurantTable::toSavedRestaurantTableDto).orElseThrow();
    }

}
