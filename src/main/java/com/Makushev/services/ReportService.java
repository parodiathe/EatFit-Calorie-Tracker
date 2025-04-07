package com.Makushev.services;

import com.Makushev.exception.UserException;
import com.Makushev.responses.CaloriesCheckResponse;
import com.Makushev.responses.DailyReportResponse;

import java.time.LocalDate;
import java.util.Map;

public interface ReportService {
    DailyReportResponse getDailyHistory(Long id, LocalDate date) throws UserException;

    CaloriesCheckResponse checkDailyCalories(Long id, LocalDate date)
            throws UserException;

    Map<LocalDate, Integer> getCaloriesHistory(Long userId, LocalDate date);
}
