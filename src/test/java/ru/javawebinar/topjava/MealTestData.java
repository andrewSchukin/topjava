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

    public static final Meal MEAL1_USER = new Meal(MEAL1_ID, LocalDateTime.parse("2018-01-01T01:00:00"), "Завтрак", 500);
    public static final Meal MEAL2_USER = new Meal(MEAL1_ID + 1, LocalDateTime.parse("2018-01-01T01:15:00"), "Завтрак1", 100);
    public static final Meal MEAL3_USER = new Meal(MEAL1_ID + 2, LocalDateTime.parse("2018-02-01T01:20:00"), "Завтрак2", 200);
    public static final Meal MEAL1_ADMIN = new Meal(MEAL2_ID, LocalDateTime.parse("2018-01-01T03:00:00"), "Обед", 700);
    public static final Meal MEAL2_ADMIN = new Meal(MEAL2_ID + 1, LocalDateTime.parse("2018-01-01T03:15:00"), "Обед2", 800);
    public static final Meal MEAL3_ADMIN = new Meal(MEAL2_ID + 2, LocalDateTime.parse("2018-02-01T03:20:00"), "Обед3", 900);
    public static final Comparator comparator = Comparator.comparing(Meal::getId).thenComparing(Meal::getDateTime).thenComparing(Meal::getDescription).thenComparing(Meal::getCalories);

    public static int getMeal1UserId() {
        return USER_ID;
    }

    public static int getMeal2UserId() {
        return ADMIN_ID;
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertThat(actual).usingElementComparator(comparator).isEqualTo(Arrays.asList(expected));
    }

    public static void assertMatch(Meal actual, Meal expected) {

        assertThat(actual).usingComparator(comparator).isEqualTo(expected);
    }
}
