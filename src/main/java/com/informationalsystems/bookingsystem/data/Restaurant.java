package com.informationalsystems.bookingsystem.data;

import com.informationalsystems.bookingsystem.restaurant.SavedRestaurantDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "restaurant")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Temporal(TemporalType.TIME)
    @ElementCollection
    private List<Date> startTimes = new ArrayList<>();

    @Temporal(TemporalType.TIME)
    @ElementCollection
    private List<Date> endTimes = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Dish> dishes = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<RestaurantTable> tables = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToOne
    private User userEntity;

    public static SavedRestaurantDto toSavedRestaurantDto(Restaurant restaurant) {
        return SavedRestaurantDto.builder()
                .id(restaurant.getId())
                .phoneNumber(restaurant.getUserEntity().getPhoneNumber())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .startTimes(restaurant.getStartTimes())
                .endTimes(restaurant.getEndTimes())
                .build();
    }

}
