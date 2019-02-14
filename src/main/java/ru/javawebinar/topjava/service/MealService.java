package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

public interface MealService {

    Meal create(Meal meal, int userId);

    void update(Meal meal, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Collection<Meal> getAll();

    Meal get(int id, int userId) throws NotFoundException;;
}