package com.example.ridelink.ride.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ridelink.driver.model.Driver;
import com.example.ridelink.driver.repository.DriverRepository;
import com.example.ridelink.ride.model.Ride;
import com.example.ridelink.ride.repository.RideRepository;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;

    public RideService(
            RideRepository rideRepository,
            DriverRepository driverRepository) {

        this.rideRepository = rideRepository;
        this.driverRepository = driverRepository;
    }

    public Ride createRide(Ride ride) {

        List<Driver> availableDrivers =
                driverRepository.findByAvailableTrue();

        if (availableDrivers.isEmpty()) {
            throw new RuntimeException("No drivers available");
        }

        Driver driver = availableDrivers.get(0);

        driver.setAvailable(false);
        driverRepository.save(driver);

        ride.setDriverId(driver.getId());
        ride.setStatus("ASSIGNED");

        return rideRepository.save(ride);
    }

    public Ride getRide(Long id) {

        return rideRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ride not found"));
    }

    public Ride completeRide(Long id) {

        Ride ride = getRide(id);

        ride.setStatus("COMPLETED");

        if (ride.getDriverId() != null) {

            Driver driver = driverRepository
                    .findById(ride.getDriverId())
                    .orElseThrow(() ->
                            new RuntimeException("Driver not found"));

            driver.setAvailable(true);
            driverRepository.save(driver);
        }

        return rideRepository.save(ride);
    }
}