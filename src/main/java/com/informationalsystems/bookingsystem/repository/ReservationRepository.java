package com.informationalsystems.bookingsystem.repository;

import com.informationalsystems.bookingsystem.data.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
