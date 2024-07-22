package com.transport.transport.repository;

import com.transport.transport.model.Path;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathRepository extends CrudRepository<Path, Long> {
}
