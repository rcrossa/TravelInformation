package com.transport.transport.controller;

import com.transport.transport.model.Station;
import com.transport.transport.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stations")
public class StationController {
    @Autowired
    private StationService stationService;

    @PutMapping("/{station_id}")
    @Operation(description ="Update a Station")
    public ResponseEntity<String> updateStation(
            @PathVariable Long station_id,
            @RequestBody Station station) {
        Station updatedStation = stationService.updateStation(station_id, station.getName());
        if (updatedStation != null) {
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Station not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(description ="Create a Station")
    public ResponseEntity<Station> addStation(@RequestBody Station station) {
        Station savedStation = stationService.saveStation(station);
        return new ResponseEntity<>(savedStation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{station_id}")
    @Operation(description = "Delete a Station")
    public ResponseEntity<String> deleteStation(@PathVariable Long station_id) {
        boolean deleted = stationService.deleteStation(station_id);
        if (deleted) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Station not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(description = "Get all Station")
    public ResponseEntity<Iterable> getAllStations() {
        return new ResponseEntity<>(stationService.getAllStations(),HttpStatus.FOUND);
    }

    @GetMapping("/station/{stationId}")
    @Operation(description = "Get a Station by id")
    public ResponseEntity<Station> getPathById(@PathVariable Long stationId) {
        Station station = stationService.getStation(stationId);
        return new ResponseEntity<>(station, HttpStatus.OK);
    }
}
