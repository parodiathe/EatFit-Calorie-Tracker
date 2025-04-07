package com.Makushev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "The name can't be empty.")
    private String name;

    @Column(nullable = false)
    @Min(value = 0, message = "Caloric content cannot be negative")
    @NotNull(message = "Calories cannot be null")
    private Integer calories;

    @Column(nullable = false)
    @Min(value = 0, message = "Protein value cannot be negative")
    private Double proteins;

    @Column(nullable = false)
    @Min(value = 0, message = "Fat value cannot be negative")
    private Double fats;

    @Column(nullable = false)
    @Min(value = 0, message = "Carbohydrates value cannot be negative")
    private Double carbohydrates;


}



