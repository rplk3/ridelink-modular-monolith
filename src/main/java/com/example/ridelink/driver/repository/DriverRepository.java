package com.example.ridelink.driver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ridelink.driver.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByAvailableTrue();
}