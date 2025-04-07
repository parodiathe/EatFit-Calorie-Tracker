package com.Makushev.services.impl;

import com.Makushev.exception.DishException;
import com.Makushev.model.Dish;
import com.Makushev.repository.DishRepository;
import com.Makushev.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Override
    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish getDishById(Long id) throws DishException {
        return dishRepository.findById(id)
                .orElseThrow(() -> new DishException("Dish not found with id: " + id));
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public void deleteDish(Long id) throws DishException {
        if (!dishRepository.existsById(id)) {
            throw new DishException("Dish not found with id: " + id);
        }
        dishRepository.deleteById(id);
    }

    @Override
    public Dish updateDish(Long id, Dish updatedDish) throws DishException {
        Dish existingDish = dishRepository.findById(id)
                .orElseThrow(() -> new DishException("Dish not found with id: " + id));

        existingDish.setName(updatedDish.getName());
        existingDish.setCalories(updatedDish.getCalories());
        existingDish.setProteins(updatedDish.getProteins());
        existingDish.setFats(updatedDish.getFats());
        existingDish.setCarbohydrates(updatedDish.getCarbohydrates());

        return dishRepository.save(existingDish);
    }
}
