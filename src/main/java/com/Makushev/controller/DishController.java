package com.Makushev.controller;

import com.Makushev.exception.DishException;
import com.Makushev.model.Dish;
import com.Makushev.services.DishService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    @PostMapping
    @Operation(summary = "Создать новое блюдо", description = "Создаем новое блюдо")
    public ResponseEntity<Dish> createMeal(@RequestBody @Valid Dish dish) {
        Dish createdMeal = dishService.createDish(dish);
        return new ResponseEntity<>(createdMeal, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Получить список всех блюд", description = "Получаем список всех блюд")
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск блюдо по id", description = "Ищем блюдо по его id")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) throws DishException {
        Dish dish = dishService.getDishById(id);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить блюдо по id", description = "Обновляем блюдо по его id")
    public ResponseEntity<Dish> updateDish(@RequestBody @Valid Dish dish,
                                           @PathVariable Long id) throws DishException {
        Dish updatedDish = dishService.updateDish(id, dish);
        return new ResponseEntity<>(updatedDish, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить блюдо по id", description = "Удаляем блюдо по его id")
    public ResponseEntity<String> deleteDishById(@PathVariable Long id) throws Exception{

        dishService.deleteDish(id);
        return new ResponseEntity<>("Dish deleted", HttpStatus.ACCEPTED);
    }


}
