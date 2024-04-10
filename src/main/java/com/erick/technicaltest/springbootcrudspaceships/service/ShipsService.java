package com.erick.technicaltest.springbootcrudspaceships.service;

import com.erick.technicaltest.springbootcrudspaceships.entities.Ships;

import java.util.List;
import java.util.Optional;

// This interface will be used to define the CRUD methods for the ships
public interface ShipsService {

    // CRUD methods

    List<Ships> findAll();

    Optional<Ships> findById(Long id);

    Ships save(Ships ship);

    Optional<Ships> update(Long id, Ships ship);

    Optional<Ships> deleteById(Long id);

    List<Ships> findAllByNameContainingIgnoreCase(String name);
}
