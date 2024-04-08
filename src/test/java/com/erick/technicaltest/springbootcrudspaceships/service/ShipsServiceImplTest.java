package com.erick.technicaltest.springbootcrudspaceships.service;

import com.erick.technicaltest.springbootcrudspaceships.entities.Ships;
import com.erick.technicaltest.springbootcrudspaceships.repositories.ShipsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

// This class will be used to test the CRUD methods for the ships
class ShipsServiceImplTest {

    @Mock
    private ShipsRepository repository;

    @InjectMocks
    private ShipsServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllReturnsAllShips() {
        when(repository.findAll()).thenReturn(List.of(new Ships(), new Ships()));
        assertEquals(2, service.findAll().size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findByIdReturnsShipWhenExists() {
        Ships ship = new Ships();
        when(repository.findById(1L)).thenReturn(Optional.of(ship));
        assertEquals(ship, service.findById(1L).orElseThrow());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findByIdReturnsEmptyWhenDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), service.findById(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void saveSavesAndReturnsShip() {
        Ships ship = new Ships();
        when(repository.save(ship)).thenReturn(ship);
        assertEquals(ship, service.save(ship));
        verify(repository, times(1)).save(ship);
    }

    @Test
    void updateUpdatesAndReturnsShipWhenExists() {
        Ships ship = new Ships();
        ship.setName("New Name");
        ship.setMovie("New Movie");

        Ships existingShip = new Ships();
        existingShip.setName("Old Name");
        existingShip.setMovie("Old Movie");

        when(repository.findById(1L)).thenReturn(Optional.of(existingShip));
        when(repository.save(existingShip)).thenReturn(ship);

        assertEquals(ship, service.update(1L, ship).orElseThrow());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(existingShip);
    }

    @Test
    void updateReturnsEmptyWhenDoesNotExist() {
        Ships ship = new Ships();
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), service.update(1L, ship));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deleteByIdDeletesAndReturnsShipWhenExists() {
        Ships ship = new Ships();
        when(repository.findById(1L)).thenReturn(Optional.of(ship));
        assertEquals(ship, service.deleteById(1L).orElseThrow());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(ship);
    }

    @Test
    void deleteByIdReturnsEmptyWhenDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), service.deleteById(1L));
        verify(repository, times(1)).findById(1L);
    }
}