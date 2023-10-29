package com.informationalsystems.bookingsystem.repository;

import com.informationalsystems.bookingsystem.data.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
