package com.Makushev.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaloriesCheckResponse {
    private boolean ExceedingLimit;
    private Integer CaloriesConsumed;
    private Integer CalorieNorm;
}
