package com.erick.technicaltest.springbootcrudspaceships.repositories;

import com.erick.technicaltest.springbootcrudspaceships.entities.Ships;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// This interface extends JpaRepository to use the inherited CRUD methods
@Repository
public interface ShipsRepository extends JpaRepository<Ships, Long> {

    // These additional methods can be used to query the database
    // For example, to find a ship by name
    public Ships findByName(String name);

    // Find all the ships that have a name that contains the given string
    List<Ships> findAllByNameContainingIgnoreCase(String name);

    // Or to find a ship by movie
    List<Ships> findAllByMovie(String movie);
}
