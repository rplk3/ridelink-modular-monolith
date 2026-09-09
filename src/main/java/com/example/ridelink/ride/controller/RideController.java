package com.example.ridelink.ride.controller;

import org.springframework.web.bind.annotation.*;

import com.example.ridelink.ride.model.Ride;
import com.example.ridelink.ride.service.RideService;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping
    public Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @GetMapping("/{id}")
    public Ride getRide(@PathVariable Long id) {
        return rideService.getRide(id);
    }

    @PatchMapping("/{id}/complete")
    public Ride completeRide(@PathVariable Long id) {
        return rideService.completeRide(id);
    }
}