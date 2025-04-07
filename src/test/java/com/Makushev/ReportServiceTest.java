package com.Makushev;

import com.Makushev.exception.UserException;
import com.Makushev.model.Dish;
import com.Makushev.model.FoodIntake;
import com.Makushev.model.User;
import com.Makushev.repository.FoodIntakeRepository;
import com.Makushev.repository.UserRepository;
import com.Makushev.responses.CaloriesCheckResponse;
import com.Makushev.responses.DailyReportResponse;
import com.Makushev.services.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FoodIntakeRepository foodIntakeRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetDailyHistory() throws UserException {
        Long userId = 1L;
        LocalDate date = LocalDate.of(2025, 4, 3);

        User user = new User();
        user.setId(userId);
        user.setDailyCalories(2700);

        Dish dish1 = new Dish();
        dish1.setCalories(700);

        Dish dish2 = new Dish();
        dish2.setCalories(400);

        FoodIntake foodIntake = new FoodIntake();
        foodIntake.setDate(date);
        foodIntake.setDishes(Arrays.asList(dish1, dish2));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(foodIntakeRepository.findByUserIdAndDate(userId, date)).thenReturn(Collections.singletonList(foodIntake));

        DailyReportResponse response = reportService.getDailyHistory(userId, date);

        assertNotNull(response, "Ответ не должен быть null");
        assertEquals(date, response.getDate(), "Дата должна совпадать");
        assertEquals(1100, response.getTotalCalories(), "Общее количество калорий должно быть 1100");
        assertEquals(2700, response.getCaloriesNorm(), "Норма калорий должна быть 2000");

        verify(userRepository, times(1)).findById(userId);
        verify(foodIntakeRepository, times(1)).findByUserIdAndDate(userId, date);
    }

    @Test
    void testCheckDailyCalories() throws UserException {
        Long userId = 1L;
        LocalDate date = LocalDate.of(2025, 4, 3);

        User user = new User();
        user.setId(userId);
        user.setDailyCalories(2700);

        Dish dish1 = new Dish();
        dish1.setCalories(700);

        Dish dish2 = new Dish();
        dish2.setCalories(400);

        FoodIntake foodIntake = new FoodIntake();
        foodIntake.setDate(date);
        foodIntake.setDishes(Arrays.asList(dish1, dish2));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(foodIntakeRepository.findByUserIdAndDate(userId, date)).thenReturn(Collections.singletonList(foodIntake));

        CaloriesCheckResponse response = reportService.checkDailyCalories(userId, date);

        assertNotNull(response, "Ответ не должен быть null");
        assertFalse(response.isExceedingLimit(), "Норма калорий не должна быть превышена");
        assertEquals(1100, response.getCaloriesConsumed(), "Потреблено должно быть 500 калорий");
        assertEquals(2700, response.getCalorieNorm(), "Дневная норма должна быть 2000 калорий");

        verify(userRepository, times(1)).findById(userId);
        verify(foodIntakeRepository, times(1)).findByUserIdAndDate(userId, date);
    }

    @Test
    void testGetCaloriesHistory() {
        Long userId = 1L;
        LocalDate date = LocalDate.of(2025, 4, 3);

        Dish dish1 = new Dish();
        dish1.setCalories(700);

        Dish dish2 = new Dish();
        dish2.setCalories(400);

        FoodIntake foodIntake = new FoodIntake();
        foodIntake.setDate(date);
        foodIntake.setDishes(Arrays.asList(dish1, dish2));

        when(foodIntakeRepository.findByUserIdAndDate(userId, date)).thenReturn(Collections.singletonList(foodIntake));

        Map<LocalDate, Integer> history = reportService.getCaloriesHistory(userId, date);

        assertNotNull(history, "История не должна быть null");
        assertEquals(1, history.size(), "МИНИМУМ одна запись в истории");
        assertEquals(1100, history.get(date), "Калории за дату должны быть 1100");

        verify(foodIntakeRepository, times(1)).findByUserIdAndDate(userId, date);
    }
}