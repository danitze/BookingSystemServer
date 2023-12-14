package com.informationalsystems.bookingsystem.data;

import com.informationalsystems.bookingsystem.dish.SavedDishWithCountDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reservation_dish")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dish dish;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Reservation reservation;

    @Column(name = "count")
    private Integer count;

    public static SavedDishWithCountDto toSavedDishWithCountDto(ReservationDish reservationDish) {
        return SavedDishWithCountDto.builder()
                .dto(Dish.toSavedDishDto(reservationDish.getDish()))
                .count(reservationDish.getCount())
                .build();
    }

}
