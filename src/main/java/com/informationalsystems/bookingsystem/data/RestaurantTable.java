package com.informationalsystems.bookingsystem.data;

import com.informationalsystems.bookingsystem.table.SavedRestaurantTableDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurant_table")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seats")
    private Integer seats;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "position")
    private RestaurantTablePosition position;

    @Column(name = "description")
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();

    public static SavedRestaurantTableDto toSavedRestaurantTableDto(RestaurantTable table) {
        return SavedRestaurantTableDto.builder()
                .id(table.getId())
                .seats(table.getSeats())
                .position(table.getPosition())
                .description(table.getDescription())
                .build();
    }
}
