package com.Makushev.controller;

import com.Makushev.exception.UserException;
import com.Makushev.responses.CaloriesCheckResponse;
import com.Makushev.responses.DailyReportResponse;
import com.Makushev.services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("{userId}/daily-count")
    @Operation(summary = "Получить отчёт за день",
            description = "Получаем информационные отчёт за день")
    public ResponseEntity<DailyReportResponse> getDailyHistory(@PathVariable Long userId,
                                                               @RequestParam LocalDate date)
            throws UserException {

        DailyReportResponse report = reportService.getDailyHistory(userId, date);
        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }

    @GetMapping("{userId}/check-calories")
    @Operation(summary = "Проверить уложились ли в норму",
            description = "Проверям уложился ли наш пользователь в норму")
    public ResponseEntity<CaloriesCheckResponse> checkDailyCalories(@PathVariable Long userId,
                                                                    @RequestParam LocalDate date)
            throws UserException {

        CaloriesCheckResponse response = reportService.checkDailyCalories(userId, date);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/history")
    @Operation(summary = "Получить количество потребленных калорий за указанную дату ",
            description = "Получаем количество потребленных калори за указанную дату")
    public ResponseEntity<Map<LocalDate, Integer>> getCaloriesHistory(@PathVariable Long userId,
                                                                      @RequestParam LocalDate date) {
        Map<LocalDate, Integer> history = reportService.getCaloriesHistory(userId, date);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
