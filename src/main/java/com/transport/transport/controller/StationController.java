package com.transport.transport.controller;

import com.transport.transport.model.Station;
import com.transport.transport.service.StationService;
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
    public ResponseEntity<String> updateStation(
            @PathVariable Long station_id,
            @RequestBody Station station) {
        Station updatedStation = stationService.updateStation(station_id, station.getName());
        if (updatedStation != null) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Station not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Station> addStation(@RequestBody Station station) {
        Station savedStation = stationService.saveStation(station);
        return new ResponseEntity<>(savedStation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{station_id}")
    public ResponseEntity<String> deleteStation(@PathVariable Long station_id) {
        boolean deleted = stationService.deleteStation(station_id);
        if (deleted) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Station not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Iterable> getAllStations() {
        return new ResponseEntity<>(stationService.getAllStations(),HttpStatus.FOUND);
    }
}
