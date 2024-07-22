package com.transport.transport.service;

import com.transport.transport.exception.StationNotFoundException;
import com.transport.transport.model.Station;
import com.transport.transport.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    public Station saveStation(Station station) {
        return stationRepository.save(station);
    }

    public Station updateStation(Long id, String name) {
        Station station = stationRepository.findById(id).orElse(null);
        if (station != null) {
            station.setName(name);
            stationRepository.save(station);
        }
        return station;
    }
    public boolean deleteStation(Long id) {
        if (stationRepository.existsById(id)) {
            stationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Iterable<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStation(Long id) {
        String message = "Not found path with id: " + id;
        return stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException(message,id));
    }
}
