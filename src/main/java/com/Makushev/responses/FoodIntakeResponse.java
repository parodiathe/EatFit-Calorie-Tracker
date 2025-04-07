package com.Makushev.responses;

import com.Makushev.model.Dish;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodIntakeResponse {

    private Long id;
    private LocalDate date;
    private Long userId;
    private List<Dish> dishes;
}
