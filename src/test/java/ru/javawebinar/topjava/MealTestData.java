package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


public class MealTestData {

    public static final int MEAL1_ID = 100002;
    public static final int MEAL2_ID = 100005;

    public static final Meal MEAL1_USER = new Meal(MEAL1_ID, LocalDateTime.of(2018, 1, 1, 1, 0), "Завтрак", 500);
    public static final Meal MEAL2_USER = new Meal(MEAL1_ID + 1, LocalDateTime.of(2018, 1, 1, 1, 15), "Завтрак1", 100);
    public static final Meal MEAL3_USER = new Meal(MEAL1_ID + 2, LocalDateTime.of(2018, 1, 2, 1, 20), "Завтрак2", 200);
    public static final Meal MEAL1_ADMIN = new Meal(MEAL2_ID, LocalDateTime.of(2018, 1, 1, 3, 0), "Обед", 700);
    public static final Meal MEAL2_ADMIN = new Meal(MEAL2_ID + 1, LocalDateTime.of(2018, 1, 1, 3, 15), "Обед2", 800);
    public static final Meal MEAL3_ADMIN = new Meal(MEAL2_ID + 2, LocalDateTime.of(2018, 1, 2, 3, 20), "Обед3", 900);

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(expected));
    }

    public static void assertMatch(Meal actual, Meal expected) {

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}
