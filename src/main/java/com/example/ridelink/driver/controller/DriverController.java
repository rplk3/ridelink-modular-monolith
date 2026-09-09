package com.example.ridelink.driver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.ridelink.driver.model.Driver;
import com.example.ridelink.driver.service.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

    @GetMapping("/available")
    public List<Driver> getAvailableDrivers() {
        return driverService.getAvailableDrivers();
    }

    @PatchMapping("/{id}/availability")
    public Driver updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean available) {

        return driverService.updateAvailability(id, available);
    }
}