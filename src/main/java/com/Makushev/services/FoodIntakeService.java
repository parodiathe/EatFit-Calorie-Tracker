package com.Makushev.services;

import com.Makushev.exception.FoodIntakeException;
import com.Makushev.exception.UserException;
import com.Makushev.model.FoodIntake;
import com.Makushev.responses.DailyReportResponse;

import java.time.LocalDate;
import java.util.List;

public interface FoodIntakeService {

    FoodIntake addFoodIntake(Long id, List<Long> dishIds, LocalDate date) throws UserException;

    List<FoodIntake> getFoodIntakesByUserId(Long userId) throws FoodIntakeException, UserException;

    void deleteFoodIntake(Long id) throws FoodIntakeException;
}
