package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

public interface MealService {

    Meal create(Meal meal, int userId);

    void update(Meal meal, int id, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Collection<Meal> getAll();

    Collection<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate);

    Meal get(int id, int userId) throws NotFoundException;

    ;
}