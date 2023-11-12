package com.informationalsystems.bookingsystem.data;

import com.informationalsystems.bookingsystem.dish.SavedDishDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dish")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Reservation> reservations = new HashSet<>();

    public static SavedDishDto toSavedDishDto(Dish dish) {
        return SavedDishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .build();
    }

}
