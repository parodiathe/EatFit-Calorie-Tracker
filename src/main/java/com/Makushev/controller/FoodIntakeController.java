package com.Makushev.controller;

import com.Makushev.exception.FoodIntakeException;
import com.Makushev.exception.UserException;
import com.Makushev.model.FoodIntake;
import com.Makushev.requests.FoodIntakeRequest;
import com.Makushev.services.impl.FoodIntakeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/food-intakes")
@RequiredArgsConstructor
public class FoodIntakeController {

    private final FoodIntakeServiceImpl foodIntakeService;

    @PostMapping
    @Operation(summary = "Создать прием пищи", description = "Создаем прием пищи")
    public ResponseEntity<FoodIntake> createFoodIntake(@RequestBody FoodIntakeRequest req)
            throws UserException {

        FoodIntake foodIntake = foodIntakeService.addFoodIntake(
                req.getUserId(),
                req.getDishIds(),
                req.getDate()
        );
        return new ResponseEntity<>(foodIntake, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Получить прием пищи по id", description = "Получаем прием пищи по id")
    public ResponseEntity<List<FoodIntake>> getFoodIntakesByUserId(@PathVariable Long userId)
            throws UserException {

        List<FoodIntake> foodIntakes = foodIntakeService.getFoodIntakesByUserId(userId);
        return new ResponseEntity<>(foodIntakes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить прием пищи по id", description = "Удаляем прием пищи по id")
    public ResponseEntity<String> deleteFoodInstake(@PathVariable Long id)
            throws FoodIntakeException {

        foodIntakeService.deleteFoodIntake(id);
        return new ResponseEntity<>("FoodInstake deleted", HttpStatus.ACCEPTED);
    }
}
