package com.Makushev;

import com.Makushev.exception.DishException;
import com.Makushev.model.Dish;
import com.Makushev.repository.DishRepository;
import com.Makushev.services.impl.DishServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishServiceTest {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishServiceImpl dishService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDish() {
        Dish dish = new Dish();
        dish.setId(1L);
        dish.setName("Chicken");
        dish.setCalories(700);

        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        Dish createdDish = dishService.createDish(dish);

        assertNotNull(createdDish, "Созданное блюдо не должно быть null");
        assertEquals("Chicken", createdDish.getName(), "Название блюда должно совпадать");
        assertEquals(700, createdDish.getCalories(), "Калорийность должна совпадать");

        verify(dishRepository, times(1)).save(any(Dish.class));
    }

    @Test
    void testGetDishById() throws DishException {
        Long dishId = 1L;
        Dish dish = new Dish();
        dish.setId(dishId);
        dish.setName("Chicken");
        dish.setCalories(700);

        when(dishRepository.findById(dishId)).thenReturn(Optional.of(dish));

        Dish foundDish = dishService.getDishById(dishId);

        assertNotNull(foundDish, "Найденное блюдо не должно быть null");
        assertEquals(dishId, foundDish.getId(), "ID блюда должно совпадать");
        assertEquals("Chicken", foundDish.getName(), "Название блюда должно совпадать");

        verify(dishRepository, times(1)).findById(dishId);
    }


    @Test
    void testGetAllDishes() {
        // Подготовка данных
        Dish dish1 = new Dish();
        dish1.setId(1L);
        dish1.setName("Chicken");

        Dish dish2 = new Dish();
        dish2.setId(2L);
        dish2.setName("Soap");

        when(dishRepository.findAll()).thenReturn(Arrays.asList(dish1, dish2));

        List<Dish> dishes = dishService.getAllDishes();

        assertNotNull(dishes, "Список блюд не должен быть null");
        assertEquals(2, dishes.size(), "Должны быть возвращены два блюда");
        assertEquals("Chicken", dishes.get(0).getName(), "Первое блюдо должно быть Chicken");
        assertEquals("Soap", dishes.get(1).getName(), "Второе блюдо должно быть Soap");

        verify(dishRepository, times(1)).findAll();
    }
}