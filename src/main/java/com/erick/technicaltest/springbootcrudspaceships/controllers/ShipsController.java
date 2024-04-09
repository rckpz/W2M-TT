package com.erick.technicaltest.springbootcrudspaceships.controllers;

import com.erick.technicaltest.springbootcrudspaceships.entities.Ships;
import com.erick.technicaltest.springbootcrudspaceships.exception.ResourceNotFoundException;
import com.erick.technicaltest.springbootcrudspaceships.repositories.ShipsRepository;
import com.erick.technicaltest.springbootcrudspaceships.service.ShipsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.*;

// This class will be used to handle the CRUD operations for the ships
@RestController
@RequestMapping("/api/ships")
public class ShipsController {

    // Injecting the ShipsRepository
    @Autowired
    private ShipsRepository repository;

    // Injecting the ShipsService
    @Autowired
    private ShipsService service;

    // Implementing the CRUD methods

    // Create ship (Create) and validate the fields
    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody Ships ship, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ship));
    }

    // List all ships (Read) and paginate the results to 5 per page
    @GetMapping
    public List<Ships> list(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Ships> shipsPage = repository.findAll(pageable);
        if (shipsPage.isEmpty()) {
            throw new ResourceNotFoundException("Ships");
        }
        return shipsPage.getContent();
    }


    // View a ship (Read)
    @GetMapping("/{id}")
    public ResponseEntity<?> view (@PathVariable Long id) {
        Optional<Ships> shipsOptional = service.findById(id);
        if (shipsOptional.isPresent()) {
            return ResponseEntity.ok(shipsOptional.orElseThrow());
        }
        throw new ResourceNotFoundException("Ship", "id", id);
    }

    // View all the ships that have a name that contains the given string (Read)
    @GetMapping("/name/{name}")
    public ResponseEntity<?> viewByName (@PathVariable String name) {
        List<Ships> ships = repository.findAllByNameContainingIgnoreCase(name);
        if (ships.isEmpty()) {
            throw new ResourceNotFoundException("Ship" + " with the name " + name);
        }
        return ResponseEntity.ok(ships);
    }


    // Update a ship (Update) and validate the fields
    @PutMapping("/{id}")
    public ResponseEntity<?> update ( @Valid @RequestBody Ships ship, BindingResult result,@PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation (result);
        }

        Optional<Ships> shipsOptional = service.update(id, ship);
        if (shipsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(shipsOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a ship (Delete)
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Optional<Ships> shipsOptional = service.deleteById(id);
        if (shipsOptional.isPresent()) {
            return ResponseEntity.ok(shipsOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // Method to validate the fields
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
