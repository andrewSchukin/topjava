package ru.javawebinar.topjava.repository.inmemory;


import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private Comparator<Meal> comparator = Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder());

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
        for (Meal meal : MealsUtil.MEALS) {
            save(new Meal(meal.getDateTime(), "user's meal number 2: " + meal.getDescription(), meal.getCalories()), 2);
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        repository.putIfAbsent(userId, new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            return repository.get(userId).computeIfAbsent(meal.getId(), key -> meal);
        }
        // treat case: update, but absent in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (key, value) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.get(userId) != null ? repository.get(userId).remove(id, repository.get(userId).get(id)) : false;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId) != null ? repository.get(userId).get(id) : null;
    }

    @Override
    public List<Meal> getAllForPeriod(int userId, LocalDate startDate, LocalDate endDate) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime().toLocalDate(), startDate, endDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getOrDefault(userId, new ConcurrentHashMap<>())
                .values()
                .stream()
                .sorted(comparator).collect(Collectors.toList());
    }
}

