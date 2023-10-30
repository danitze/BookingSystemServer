package com.informationalsystems.bookingsystem.table;

import com.informationalsystems.bookingsystem.data.Restaurant;
import com.informationalsystems.bookingsystem.data.RestaurantTable;
import com.informationalsystems.bookingsystem.data.User;
import com.informationalsystems.bookingsystem.repository.TableRepository;
import com.informationalsystems.bookingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class TableService {

    private final UserRepository userRepository;

    private final TableRepository tableRepository;

    public String addTable(Principal principal, RegisterTableDto dto) {
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
        return "Saved successfully";
    }

}
