package com.informationalsystems.bookingsystem.repository;

import com.informationalsystems.bookingsystem.data.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id = :pid")
    void deleteByPid(@Param("pid") Long theId);

}
