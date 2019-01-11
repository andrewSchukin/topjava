package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> result = getFilteredWithExceededByCycle(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        for (UserMealWithExceed el: result)
            System.out.println(el);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        Map<LocalDate, Integer> caloriesSumByLocalDate =
                mealList.stream().collect(Collectors.groupingBy(s -> s.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                        .filter( s -> s.getDateTime().toLocalTime().compareTo(startTime) >= 0 && s.getDateTime().toLocalTime().compareTo(endTime) <= 0)
                        .map( s -> new UserMealWithExceed(s.getDateTime(), s.getDescription(), s.getCalories(), caloriesSumByLocalDate.get(s.getDateTime().toLocalDate()) > caloriesPerDay))
                        .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumByLocalDate = new HashMap<>();
        List<UserMealWithExceed> result = new ArrayList<>();

        for (UserMeal userMeal : mealList) {
            caloriesSumByLocalDate.put(userMeal.getDateTime().toLocalDate(), caloriesSumByLocalDate.getOrDefault(userMeal.getDateTime().toLocalDate(), 0) + userMeal.getCalories());
        }

        for (UserMeal userMeal : mealList) {
            if (userMeal.getDateTime().toLocalTime().compareTo(startTime) >= 0 && userMeal.getDateTime().toLocalTime().compareTo(endTime) <= 0)
                result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), caloriesSumByLocalDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
        }

        return result;
    }
}
