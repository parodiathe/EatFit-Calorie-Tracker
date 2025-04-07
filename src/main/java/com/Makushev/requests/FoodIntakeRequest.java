package com.Makushev.requests;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FoodIntakeRequest {

    private Long userId;
    private LocalDate date;
    private List<Long> dishIds;

}
