package com.example.Rocket.controller;

import com.example.Rocket.dto.RocketDto;
import com.example.Rocket.service.RocketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/rocket")
public class RocketController {

    private RocketService rocketService;

    // Add Rocket REST API endpoint Postmapping map to incoming http post request to below method
    @PostMapping
    public ResponseEntity<RocketDto> createRocket(@RequestBody RocketDto rocketDto) { //RequestBody will extract the json from HTTP request and convert it to RocketDto object.
        RocketDto savedRocket = rocketService.createRocket(rocketDto);
        return new ResponseEntity<>(savedRocket, HttpStatus.CREATED);
    }

    // Get Rocket Rest API endpoint
    @GetMapping("{id}")
    public ResponseEntity<RocketDto> getRocketById(@PathVariable("id") Long rocketId) {
        RocketDto rocket = rocketService.getRocketById(rocketId);
        return new ResponseEntity<>(rocket, HttpStatus.OK);
    }
    // Build Get All Rockets REST API endpoint
    @GetMapping
    public ResponseEntity<List<RocketDto>> getAllRockets() {
        List<RocketDto> rockets = rocketService.getAllRockets();
        return new ResponseEntity<>(rockets, HttpStatus.OK);
    }

    //Build Update Rocket REST API endpoint
    @PutMapping("{id}")
    public ResponseEntity<RocketDto> updateRocket(@PathVariable("id") Long rocketId, @RequestBody RocketDto updatedRocket) {
        RocketDto rocket = rocketService.updateRocket(rocketId, updatedRocket);
        return new ResponseEntity<>(rocket, HttpStatus.OK);
    }

    //Build Delete Rocket REST API endpoint
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRocket(@PathVariable("id") Long rocketId) {
        rocketService.deleteRocket(rocketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
