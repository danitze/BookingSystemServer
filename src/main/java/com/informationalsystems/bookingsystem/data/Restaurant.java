package com.informationalsystems.bookingsystem.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "restaurant")
@Data
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
    private List<String> startTimes = new ArrayList<>();


    @Temporal(TemporalType.TIME)
    @ElementCollection
    private List<String> endTimes = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Dish> dishes = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<RestaurantTable> tables = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<MenuItem> menu = new HashSet<>();

    @OneToOne
    private User userEntity;

}
