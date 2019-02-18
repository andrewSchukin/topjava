package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.lang.annotation.Retention;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private Comparator<Meal> comparator = Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder());

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
        for (Meal meal : MealsUtil.MEALS) {
            save(new Meal(meal.getDateTime(), meal.getDescription(), meal.getCalories()), 2);
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            putIntoRepository(meal, userId);
            return meal;
        }
        if (isAccess(meal.getId(), userId)) {
            putIntoRepository(meal, userId);
            // treat case: update, but absent in storage
            return repository.get(userId).computeIfPresent(meal.getId(), (key, value) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (isAccess(id, userId)) {
            return repository.get(userId).remove(id, repository.get(userId).get(id));
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return isAccess(id, userId) ? repository.get(userId).get(id) : null;
    }

    @Override
    public List<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getOrDefault(userId, new ConcurrentHashMap<>())
                .values()
                .stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime().toLocalDate(), startDate, endDate))
                .sorted(comparator).collect(Collectors.toList());
    }

    private boolean isAccess(int id, int userId) {
        if (repository.containsKey(userId) && repository.get(userId) != null)
            if (repository.get(userId).containsKey(id))
                return true;
        return false;
    }

    private void putIntoRepository(Meal meal, int userId) { //computeIfPresent
        if (repository.containsKey(userId) && repository.get(userId) != null) {
            repository.get(userId).put(meal.getId(), meal);
        } else {
            repository.put(userId, new ConcurrentHashMap<>());
            repository.get(userId).put(meal.getId(), meal);
        }
    }
}

