package com.informationalsystems.bookingsystem.repository;

import com.informationalsystems.bookingsystem.data.ReservationDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDishRepository extends JpaRepository<ReservationDish, Long> {
}
