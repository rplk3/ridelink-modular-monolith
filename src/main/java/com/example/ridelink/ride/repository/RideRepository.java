package com.example.ridelink.ride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ridelink.ride.model.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {
}