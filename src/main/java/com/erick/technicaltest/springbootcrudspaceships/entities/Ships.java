package com.erick.technicaltest.springbootcrudspaceships.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

// This class will be used to instantiate the Ships object
@Setter
@Getter
@Entity
public class Ships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "is required")
    private String name;

    @NotEmpty(message = "is required")
    private String movie;


}
