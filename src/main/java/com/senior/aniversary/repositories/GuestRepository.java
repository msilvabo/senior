package com.senior.aniversary.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.senior.aniversary.entities.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>, JpaSpecificationExecutor<Guest> {

    List<Guest> findByType(String type);
    List<Guest> findByNameContainingIgnoreCase(String name);
    List<Guest> findByQuantityGreaterThan(Integer quantity);
    List<Guest> findByDateRegisterBetween(LocalDate start, LocalDate end);
}
