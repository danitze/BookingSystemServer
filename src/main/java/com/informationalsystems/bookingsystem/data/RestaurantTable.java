package com.informationalsystems.bookingsystem.data;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seats")
    private Integer seats;

    @Enumerated
    @Column(name = "position")
    private RestaurantTablePosition position;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

}
