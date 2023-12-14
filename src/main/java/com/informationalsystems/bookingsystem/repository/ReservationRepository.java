package com.informationalsystems.bookingsystem.repository;

import com.informationalsystems.bookingsystem.data.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Modifying
    @Query("DELETE FROM Reservation r WHERE r.id = :pid")
    void deleteByPid(@Param("pid") Long theId);
}
