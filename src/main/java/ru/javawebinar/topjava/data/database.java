package ru.javawebinar.topjava.data;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class database {

    private final static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static List<Meal> meals = new ArrayList<>();

    static
    {
        meals.add(new Meal(atomicInteger.getAndIncrement(), LocalDateTime.of( 2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(atomicInteger.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(atomicInteger.getAndIncrement(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(atomicInteger.getAndIncrement(), LocalDateTime.of( 2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(atomicInteger.getAndIncrement(), LocalDateTime.of( 2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(atomicInteger.getAndIncrement(), LocalDateTime.of( 2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    private static List<MealTo> mealsTo = null;

    public static List<MealTo> getMealsList(){
        mealsTo = MealsUtil.getMealsWithExcess(meals, 2000);
        return mealsTo;
    }

    public static void addMeal(LocalDateTime dateTime, String description, int calories){
        meals.add(new Meal(atomicInteger.getAndIncrement(), dateTime, description, calories));
    }

    public static void editMeal(int id, LocalDateTime dateTime, String description, int calories){
        for (Meal meal : meals){
            if (meal.getId()== id){
                meal.setDateTime(dateTime);
                meal.setDescription(description);
                meal.setCalories(calories);
            }
        }
    }

    public static Meal getMealById(int id) {
        for (Meal meal : meals){
            if (meal.getId() == id) {
                return meal;
            }
        }
        return null;
    }

    public static void deleteMeal(int id) {
        Integer idForDelete = null;
        for (int i = 0; i < meals.size(); i++){
            if (meals.get(i).getId() == id) {
                idForDelete = i;
            }
        }

        if (idForDelete != null) {
            meals.remove(idForDelete.intValue());
            atomicInteger.decrementAndGet();
        }
    }
}
