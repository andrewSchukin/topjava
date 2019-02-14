package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.lang.annotation.Retention;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, User> mealMapWithUser = new ConcurrentHashMap<>();

    @Autowired
    private UserRepository userRepository;

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        mealMapWithUser.put(meal.getId(), userRepository.get(userId));
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (isAccess(id, userId)) {
            boolean isRemove = false;
            isRemove = repository.remove(id, repository.get(id));
            if (isRemove)
                mealMapWithUser.remove(id);
            return isRemove;
        }

        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return isAccess(id, userId) ? repository.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll() {
        Comparator comparator = new Comparator<Meal>(){
            @Override
            public int compare(Meal o1, Meal o2) {
                int result = 0;
                if (!o1.getDateTime().equals(o2.getDateTime())) {
                    result += o1.getDateTime().isAfter(o2.getDateTime()) ? -1 : 1;
                }
                return result;
            }
        };
        return (Collection) repository.values().stream()
                .sorted(comparator).collect(Collectors.toList());
    }

    private boolean isAccess (int id, int userId) {
        return mealMapWithUser.get(id).getId() == userId;
    }
}

