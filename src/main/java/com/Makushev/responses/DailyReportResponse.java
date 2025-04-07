package com.Makushev.responses;

import com.Makushev.model.FoodIntake;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportResponse {
    private LocalDate date;
    private Integer totalCalories;
    private List<FoodIntake> foodIntakes;
    private Integer CaloriesNorm;
}
