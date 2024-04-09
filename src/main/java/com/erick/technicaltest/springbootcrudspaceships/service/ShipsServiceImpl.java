package com.erick.technicaltest.springbootcrudspaceships.service;

import com.erick.technicaltest.springbootcrudspaceships.entities.Ships;
import com.erick.technicaltest.springbootcrudspaceships.repositories.ShipsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// This class will be used to implement the CRUD methods for the ships
@Service
public class ShipsServiceImpl implements ShipsService{

    // Injecting the repository to the service
    @Autowired
    private ShipsRepository repository;

    // Implementing the CRUD methods

    @Transactional(readOnly = true)
    @Override
    public List<Ships> findAll() {
        return repository.findAll();
    }

    @Transactional (readOnly = true)
    @Cacheable(value = "ship", key = "#id")
    @Override
    public Optional<Ships> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @CachePut(value = "ship", key = "#result.id")
    @Override
    public Ships save(Ships ship) {
        return repository.save(ship);
    }

    @Transactional
    @CachePut(value = "ship", key = "#id")
    @Override
    public Optional<Ships> update(Long id, Ships ship) {
        Optional <Ships> shipOptional = repository.findById(id);
        if(shipOptional.isPresent()) {
            Ships shipsDb = shipOptional.orElseThrow();
            shipsDb.setName(ship.getName());
            shipsDb.setMovie(ship.getMovie());
            return Optional.of(repository.save(shipsDb));
        }
        return shipOptional;
    }

    @Transactional
    @CacheEvict(value = {"ship", "list"}, key = "#id")
    @Override
    public Optional<Ships> deleteById(Long id) {
        Optional <Ships> shipOptional = repository.findById(id);
        shipOptional.ifPresent(shipsDb -> repository.delete(shipsDb));
        return shipOptional;
    }

}
