package com.example.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flight.entity.Airline;



@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
    // You can define custom query methods if needed
	Airline findByCode(String code);
}
