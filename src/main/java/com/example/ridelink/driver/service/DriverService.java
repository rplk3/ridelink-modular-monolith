package com.example.ridelink.driver.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ridelink.driver.model.Driver;
import com.example.ridelink.driver.repository.DriverRepository;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver createDriver(Driver driver) {
        driver.setAvailable(true);
        return driverRepository.save(driver);
    }

    public List<Driver> getAvailableDrivers() {
        return driverRepository.findByAvailableTrue();
    }

    public Driver updateAvailability(Long id, boolean available) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setAvailable(available);

        return driverRepository.save(driver);
    }
}