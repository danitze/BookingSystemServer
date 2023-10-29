package com.informationalsystems.bookingsystem.repository;

import com.informationalsystems.bookingsystem.data.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
}
