package com.erick.technicaltest.springbootcrudspaceships.controllers;

import com.erick.technicaltest.springbootcrudspaceships.entities.Ships;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WebAppConfiguration
class ShipsControllerTest {

    private final static String BASE_URL = "http://localhost:8080/api/ships";

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void create() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(String.valueOf(shipToJson(buildShip()))))
                .andReturn();
        assertEquals(201, mockMvcResult.getResponse().getStatus());
    }

    @Test
    void list() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL).
                accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mockMvcResult.getResponse().getStatus());
    }

    @Test
    void viewById() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/2").
                accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, mockMvcResult.getResponse().getStatus());
    }

    @Test
    void viewByName() throws Exception {
        String name = "Test Ship";
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/name/" + name)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200, mockMvcResult.getResponse().getStatus());
    }

    @Test
    void update() throws Exception {
        Ships updatedShip = buildShip();
        updatedShip.setName("Updated Ship");
        updatedShip.setMovie("Updated Movie");
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/2")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(String.valueOf(shipToJson(updatedShip))))
                .andReturn();
        assertEquals(201, mockMvcResult.getResponse().getStatus());
    }

    @Test
    void delete() throws Exception {
        MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200, mockMvcResult.getResponse().getStatus());
    }

    private String shipToJson(Ships ship){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(ship);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Ships buildShip() {
        Ships ship = new Ships();
        ship.setName("Test Ship");
        ship.setMovie("Test Movie");
        return ship;
    }
}