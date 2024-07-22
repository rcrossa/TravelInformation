package com.transport.transport.repository;


import com.transport.transport.model.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends CrudRepository <Station, Long> {
}
