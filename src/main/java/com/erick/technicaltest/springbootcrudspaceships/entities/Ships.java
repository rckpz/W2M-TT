package com.erick.technicaltest.springbootcrudspaceships.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

// This class will be used to instantiate the Ships object
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Ships implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "is required")
    private String name;

    @NotEmpty(message = "is required")
    private String movie;
}
