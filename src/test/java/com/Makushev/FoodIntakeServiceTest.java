package com.Makushev;

import com.Makushev.exception.FoodIntakeException;
import com.Makushev.exception.UserException;
import com.Makushev.model.Dish;
import com.Makushev.model.FoodIntake;
import com.Makushev.model.User;
import com.Makushev.repository.DishRepository;
import com.Makushev.repository.FoodIntakeRepository;
import com.Makushev.repository.UserRepository;
import com.Makushev.services.impl.FoodIntakeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodIntakeServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private FoodIntakeRepository foodIntakeRepository;

    @InjectMocks
    private FoodIntakeServiceImpl foodIntakeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAddFoodIntake() throws UserException {
        Long userId = 1L;
        List<Long> dishIds = Arrays.asList(1L, 2L);
        LocalDate date = LocalDate.of(2025, 4, 3);

        User user = new User();
        user.setId(userId);

        Dish dish1 = new Dish();
        dish1.setId(1L);
        dish1.setCalories(700);

        Dish dish2 = new Dish();
        dish2.setId(2L);
        dish2.setCalories(400);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(dishRepository.findAllById(dishIds)).thenReturn(Arrays.asList(dish1, dish2));

        FoodIntake foodIntake = new FoodIntake();
        foodIntake.setUser(user);
        foodIntake.setDate(date);
        foodIntake.setDishes(Arrays.asList(dish1, dish2));

        when(foodIntakeRepository.save(any(FoodIntake.class))).thenReturn(foodIntake);

        FoodIntake result = foodIntakeService.addFoodIntake(userId, dishIds, date);

        assertNotNull(result, "Результат не должен быть null");
        assertEquals(user, result.getUser(), "Пользователь должен совпадать");
        assertEquals(date, result.getDate(), "Дата должна совпадать");
        assertEquals(2, result.getDishes().size(), "Должны быть добавлены два блюда");

        verify(userRepository, times(1)).findById(userId);
        verify(dishRepository, times(1)).findAllById(dishIds);
        verify(foodIntakeRepository, times(1)).save(any(FoodIntake.class));
    }

    @Test
    void testGetFoodIntakesByUserId() throws UserException {
        Long userId = 1L;

        FoodIntake foodIntake1 = new FoodIntake();
        foodIntake1.setId(1L);

        FoodIntake foodIntake2 = new FoodIntake();
        foodIntake2.setId(2L);

        when(userRepository.existsById(userId)).thenReturn(true);
        when(foodIntakeRepository.findByUserId(userId)).thenReturn(Arrays.asList(foodIntake1, foodIntake2));

        List<FoodIntake> result = foodIntakeService.getFoodIntakesByUserId(userId);

        assertNotNull(result, "Результат не должен быть null");
        assertEquals(2, result.size(), "Должны быть возвращены два приёма пищи");

        verify(userRepository, times(1)).existsById(userId);
        verify(foodIntakeRepository, times(1)).findByUserId(userId);
    }
}