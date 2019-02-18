package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {

    Meal create(Meal meal, int userId);

    void update(Meal meal, int id, int userId);

    void delete(int id, int userId) throws NotFoundException;

    List<Meal> getAllForPeriod(int userId, LocalDate startDate, LocalDate endDate);

    List<Meal> getAll(int userId);

    Meal get(int id, int userId) throws NotFoundException;

}