package com.Makushev.model;

import com.Makushev.domain.Gender;
import com.Makushev.domain.TargetType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 100)
    @NotBlank(message = "The Full Name can't be empty.")
    private String fullName;


    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email can't be empty")
    private String email;


    @Column(nullable = false)
    @NotNull(message = "Age can't be null")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age can't exceed 120")
    private Integer age;


    @Column(nullable = false)
    @NotNull(message = "Weight can't be null")
    @Min(value = 1, message = "Weight must be at least 1")
    @Max(value = 500, message = "Weight can't exceed 500")
    private Integer weight;


    @Column(nullable = false)
    @NotNull(message = "Height can't be null")
    @Min(value = 1, message = "Height must be at least 1")
    @Max(value = 300, message = "height can't exceed 300")
    private Integer height;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Gender can't be null")
    private Gender gender;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Target can't be null")
    private TargetType target;

    @Column(nullable = false)
    private Integer dailyCalories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FoodIntake> foodIntakes;

}
